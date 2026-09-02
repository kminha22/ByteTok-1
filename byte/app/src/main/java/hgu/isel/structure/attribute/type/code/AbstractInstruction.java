package hgu.isel.structure.attribute.type.code;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hgu.isel.structure.BaseBytecodeStructure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
        JsonObject json = (JsonObject) super.toJson();
        
        // 'format' 필드가 존재하면 Mnemonic을 찾아서 추가
        String mnemonic = extractMnemonic();
        if (mnemonic != null) {
            json.addProperty("--- Mnemonic", mnemonic);
        }
        
        return json;
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
            
            if (value instanceof byte[]) {
                byte[] bytes = (byte[]) value;
                if (bytes.length > 0) {
                    String opcodeHex = String.format("%02X", bytes[0]);
                    return OpcodeTable.OPCODE_NAME_MAP.getOrDefault(opcodeHex, "unknown");
                }
            } else if (value != null) {
                String hex = value.toString();
                if (hex.length() >= 2) {
                    return OpcodeTable.OPCODE_NAME_MAP.getOrDefault(hex.substring(0, 2), "unknown");
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            // format 필드가 없는 명령어 객체는 무시
        }
        return null;
    }
}