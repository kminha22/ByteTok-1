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
    // 2. 공통 toJson() 구현 (UTF8 자동 디코딩 포함)
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

                // UTF8/bytes 필드 자동 디코딩 조건문
                if (value instanceof byte[] && ("bytes".equals(fieldName) || "UTF8Information".equals(getClass().getSimpleName()))) {
                    String decoded = new String((byte[]) value, StandardCharsets.UTF_8);
                    json.addProperty("--- Decoded String", decoded);
                }

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

    protected String toStringWithIndent(int depth) {
        StringBuilder indent = new StringBuilder("  ".repeat(depth));
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" {\n");

        for (Field field : getClass().getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                String fieldName = field.getName();
                sb.append(indent).append("  ").append(fieldName).append(": ");
                sb.append(formatValue(value, depth + 1)).append("\n");

                if (value instanceof byte[] && ("bytes".equals(fieldName) || "UTF8Information".equals(getClass().getSimpleName()))) {
                    String decoded = new String((byte[]) value, StandardCharsets.UTF_8);
                    sb.append(indent).append("  ").append("--- Decoded String: ").append(decoded).append("\n");
                }
            } catch (IllegalAccessException ignored) {}
        }

        sb.append(indent).append("}");
        return sb.toString();
    }

    protected String formatValue(Object value, int depth) {
        if (value instanceof BaseBytecodeStructure) {
            return ((BaseBytecodeStructure) value).toStringWithIndent(depth);
        } else if (value instanceof Byte) {
            return String.format("0x%02X", (Byte) value);
        } else if (value instanceof byte[]) {
            return bytesToHex((byte[]) value);
        } else if (value.getClass().isArray()) {
            StringBuilder sb = new StringBuilder("[\n");
            int length = java.lang.reflect.Array.getLength(value);
            String childIndent = "  ".repeat(depth + 1);
            for (int i = 0; i < length; i++) {
                Object elem = java.lang.reflect.Array.get(value, i);
                sb.append(childIndent).append(formatValue(elem, depth + 1));
                if (i < length - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("  ".repeat(depth)).append("]");
            return sb.toString();
        } else {
            return String.valueOf(value);
        }
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