package hgu.isel.structure.attribute.type.code;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hgu.isel.structure.BaseBytecodeStructure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractInstruction extends BaseBytecodeStructure implements Instruction {

    @Override
    public String getTagName() {
        return this.getClass().getSimpleName();
    }

    // ==========================================
    // 1. Instruction 전용 toJson() (Mnemonic 추가)
    // ==========================================
    @Override
    public JsonElement toJson() {
        JsonObject originalJson = (JsonObject) super.toJson();
        JsonObject resultJson = new JsonObject();

        String mnemonic = extractMnemonic();
        
        // --- Type 값을 니모닉으로 덮어쓰거나 최상단에 배치
        if (!"unknown".equals(mnemonic)) {
            resultJson.addProperty("--- Type", mnemonic);
        } else {
            // 니모닉을 못 찾은 경우 기존 클래스명 유지
            if (originalJson.has("--- Type")) {
                resultJson.add("--- Type", originalJson.get("--- Type"));
            }
        }

        // 기존 필드 중 --- Type을 제외한 나머지 복사
        originalJson.entrySet().forEach(entry -> {
            if (!"--- Type".equals(entry.getKey())) {
                resultJson.add(entry.getKey(), entry.getValue());
            }
        });

        return resultJson;
    }

    // ==========================================
    // 2. Instruction 전용 toString() (Mnemonic 포함 formatted 텍스트)
    // ==========================================
    @Override
    public String toString() {
        return toStringWithIndent(0);
    }

    @Override
    protected String toStringWithIndent(int depth) {
        StringBuilder indent = new StringBuilder("  ".repeat(depth));
        StringBuilder sb = new StringBuilder();
        
        String mnemonic = extractMnemonic();
        sb.append(getClass().getSimpleName());
        if (mnemonic != null) {
            sb.append(" (").append(mnemonic).append(")");
        }
        sb.append(" {\n");

        for (Field field : getClass().getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                sb.append(indent).append("  ").append(field.getName()).append(": ");
                sb.append(formatValue(value, depth + 1)).append("\n");
            } catch (IllegalAccessException ignored) {}
        }

        sb.append(indent).append("}");
        return sb.toString();
    }

    // ==========================================
    // 3. Instruction 전용 tokenize() (파라미터화된 인터페이스 호환)
    // ==========================================
    @Override
    public List<String> tokenize(boolean includeTag, String delimiter) {
        List<String> tokens = new ArrayList<>();
        if (includeTag) {
            tokens.add("[" + getTagName() + "]");
        }
        
        // Mnemonic이 존재하면 첫 번째 토큰 부근에 추가
        String mnemonic = extractMnemonic();
        if (mnemonic != null) {
            tokens.add("mnemonic" + delimiter + mnemonic);
        }

        // 부모의 공통 리플렉션 토큰화 결과 활용
        List<String> baseTokens = super.tokenize();
        for (int i = 1; i < baseTokens.size(); i += 2) {
            if (i + 1 < baseTokens.size()) {
                String fieldName = baseTokens.get(i);
                String fieldValue = baseTokens.get(i + 1);
                tokens.add(fieldName + delimiter + fieldValue);
            }
        }
        
        return tokens;
    }

    // ==========================================
    // 도우미 메서드: format 필드에서 Opcode Mnemonic 추출
    // ==========================================
    private String extractMnemonic() {
        try {
            Field formatField = getClass().getDeclaredField("format");
            formatField.setAccessible(true);
            Object value = formatField.get(this);

            if (value instanceof Byte) {
                // 단일 byte를 2자리 16진수 대문자 문자열로 변환하여 바로 매핑
                String opcodeHex = String.format("%02X", (Byte) value);
                return OpcodeTable.OPCODE_NAME_MAP.getOrDefault(opcodeHex, "unknown");
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            // format 필드가 없는 경우 무시
        }
        return "unknown";
    }
}