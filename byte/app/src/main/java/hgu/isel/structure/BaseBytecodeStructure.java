package hgu.isel.structure;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseBytecodeStructure implements JsonSerializable {

    // ==========================================
    // 1. 공통 tokenize() 구현
    // ==========================================
    @Override
    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        // 클래스 이름을 첫 번째 토큰으로 넣거나 기존 규칙 적용
        tokens.add(getClass().getSimpleName());

        for (Field field : getClass().getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                // 필드명과 값의 토큰 추가
                tokens.add(field.getName());
                if (value instanceof byte[]) {
                    tokens.add(bytesToHex((byte[]) value));
                } else {
                    tokens.add(String.valueOf(value));
                }
            } catch (IllegalAccessException ignored) {}
        }
        return tokens;
    }

    // ==========================================
    // 2. 공통 toJson() 구현 
    // ==========================================
    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("--- Type", getClass().getSimpleName());

        for (Field field : getClass().getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                String fieldName = field.getName();
                json.add(fieldName, convertToJsonElement(value));

            } catch (IllegalAccessException ignored) {}
        }
        return json;
    }

    // ==========================================
    // 3. 사람이 보기 편한 toString() 구현
    // ==========================================
    @Override
    public String toString() {
        return toStringWithIndent(0);
    }

    // ByteStructure 또는 ConstantPoolInformation 목록을 가진 최상위 클래스의 toStringWithIndent 오버라이딩
    public String toStringWithIndent(int depth) {
        StringBuilder indent = new StringBuilder("  ".repeat(depth));
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getSimpleName()).append(" {\n");

        for (Field field : getClass().getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                // 상수 풀 배열인 경우 #1, #2... 인덱스 번호 부여
                if ("constantPoolInformation".equals(field.getName()) && value instanceof Object[]) {
                    sb.append(indent).append("  constantPoolInformation: [\n");
                    Object[] cpArray = (Object[]) value;
                    
                    for (int i = 0; i < cpArray.length; i++) {
                        Object item = cpArray[i];
                        if (item == null) continue;
                        
                        // #1, #2 형태의 CP 번호 추가
                        int cpIndex = i + 1; 
                        sb.append(indent).append("    #").append(cpIndex).append(" ");
                        
                        if (item instanceof BaseBytecodeStructure) {
                            sb.append(((BaseBytecodeStructure) item).toStringWithIndent(depth + 2));
                        } else {
                            sb.append(item);
                        }
                        if (i < cpArray.length - 1) sb.append(",");
                        sb.append("\n");
                    }
                    sb.append(indent).append("  ]\n");
                } else {
                    sb.append(indent).append("  ").append(field.getName()).append(": ");
                    sb.append(formatValue(field, value, depth + 1)).append("\n");
                }
            } catch (IllegalAccessException ignored) {}
        }

        sb.append(indent).append("}");
        return sb.toString();
    }

    protected String formatValue(Field field, Object value, int depth) {
        if (value == null) return "null";

        // 1. 객체 배열 처리
        if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            if (array.length == 0) return "[]";

            StringBuilder indent = new StringBuilder("  ".repeat(depth));
            StringBuilder sb = new StringBuilder("[\n");

            for (int i = 0; i < array.length; i++) {
                Object item = array[i];
                sb.append(indent);
                if (item instanceof BaseBytecodeStructure) {
                    sb.append(((BaseBytecodeStructure) item).toStringWithIndent(depth));
                } else {
                    sb.append(item);
                }
                if (i < array.length - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append(indent).append("]");
            return sb.toString();
        }

        // 2. 리스트 처리
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            if (list.isEmpty()) return "[]";

            StringBuilder indent = new StringBuilder("  ".repeat(depth));
            StringBuilder sb = new StringBuilder("[\n");

            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                sb.append(indent);
                if (item instanceof BaseBytecodeStructure) {
                    sb.append(((BaseBytecodeStructure) item).toStringWithIndent(depth));
                } else {
                    sb.append(item);
                }
                if (i < list.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append(indent).append("]");
            return sb.toString();
        }

        // 3. 하위 구조체 처리
        if (value instanceof BaseBytecodeStructure) {
            return ((BaseBytecodeStructure) value).toStringWithIndent(depth);
        }

        // 4. 바이트 배열 처리 (byte[])
        if (value instanceof byte[]) {
            byte[] bytes = (byte[]) value;
            String hex = bytesToHex(bytes);
            String fieldNameLower = field.getName().toLowerCase();

            // UTF8Information의 bytes 필드 디코딩
            if ("bytes".equals(field.getName()) && "UTF8Information".equals(getClass().getSimpleName())) {
                String decoded = new String(bytes, StandardCharsets.UTF_8);
                return hex + "\n" + "  ".repeat(depth) + "--- Decoded String: " + decoded;
            }

            if (bytes.length > 0 && bytes.length <= 8) {
                long numericVal = 0;
                for (byte b : bytes) {
                    numericVal = (numericVal << 8) | (b & 0xFF);
                }

                // 2) CP 참조 인덱스인 경우 -> (#X)
                if (fieldNameLower.contains("index")) {
                    return hex + " (#" + numericVal + ")";
                }

                // 3) length, size, count 등 크기/수치 필드인 경우 -> (X)
                if (fieldNameLower.contains("length") || fieldNameLower.contains("size") 
                        || fieldNameLower.contains("count") || fieldNameLower.contains("offset")
                        || fieldNameLower.contains("max") || fieldNameLower.contains("stack") 
                        || fieldNameLower.contains("code")|| fieldNameLower.contains("pc")|| fieldNameLower.contains("number")) {
                    return hex + " (" + numericVal + ")";
                }
            }

            return hex;
        }

        // 5. 단일 Byte 처리
        if (value instanceof Byte) {
            byte b = (Byte) value;
            int unsignedVal = Byte.toUnsignedInt(b);
            String hex = String.format("0x%02X", b);
            String fieldNameLower = field.getName().toLowerCase();

            // 1) local 변수/슬롯인 경우 -> (slot X)
            if (fieldNameLower.contains("local") 
                        && !fieldNameLower.contains("max") && !fieldNameLower.contains("length")) {
                return hex + " (slot " + unsignedVal + ")";
            }

            // 2) CP 참조 인덱스인 경우 -> (#X)
            if (fieldNameLower.contains("index")) {
                return hex + " (#" + unsignedVal + ")";
            }

            // 3) 크기/수치 필드인 경우 -> (X)
            if (fieldNameLower.contains("length") || fieldNameLower.contains("size") 
                    || fieldNameLower.contains("count") || fieldNameLower.contains("offset")
                    || fieldNameLower.contains("max") || fieldNameLower.contains("stack")) {
                return hex + " (" + unsignedVal + ")";
            }
            return hex;
        }


        return String.valueOf(value);
    }
        

    protected JsonElement convertToJsonElement(Object value) {
        if (value == null) return JsonNull.INSTANCE;
        if (value instanceof JsonSerializable) return ((JsonSerializable) value).toJson();
        if (value instanceof Byte) return new JsonPrimitive(String.format("%02X", (Byte) value));
        if (value instanceof byte[]) return new JsonPrimitive(bytesToHex((byte[]) value));

        if (value.getClass().isArray()) {
            JsonArray jsonArray = new JsonArray();
            int length = java.lang.reflect.Array.getLength(value);
            for (int i = 0; i < length; i++) {
                jsonArray.add(convertToJsonElement(java.lang.reflect.Array.get(value, i)));
            }
            return jsonArray;
        }

        if (value instanceof Collection<?>) {
            JsonArray jsonArray = new JsonArray();
            for (Object elem : (Collection<?>) value) {
                jsonArray.add(convertToJsonElement(elem));
            }
            return jsonArray;
        }

        if (value instanceof Number) return new JsonPrimitive((Number) value);
        if (value instanceof Boolean) return new JsonPrimitive((Boolean) value);
        if (value instanceof String) return new JsonPrimitive((String) value);

        return new JsonPrimitive(value.toString());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}