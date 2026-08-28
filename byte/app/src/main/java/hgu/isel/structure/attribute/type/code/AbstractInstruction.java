package hgu.isel.structure.attribute.type.code;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.lang.reflect.Field;


public abstract class AbstractInstruction implements Instruction {

    @Override
    public String getTagName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Map<String, String> getFields() {
        Map<String, String> fields = new LinkedHashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                if (value instanceof Byte) {
                    fields.put(field.getName(), String.format("%02X", value));
                } else if (value instanceof byte[]) {
                    fields.put(field.getName(), bytesToHex((byte[]) value));
                } else if (value.getClass().isArray()) {
                    // 배열 처리
                    Object[] arr = (Object[]) value;
                    StringBuilder sb = new StringBuilder("[");
                    for (int i = 0; i < arr.length; i++) {
                        Object elem = arr[i];
                        if (elem != null) {
                            sb.append(elem.toString()); // 각 객체의 toString() 호출
                        } else {
                            sb.append("null");
                        }
                    }
                    sb.append("]");
                    fields.put(field.getName(), sb.toString());
                } else {
                    fields.put(field.getName(), value.toString());
                }

            } catch (IllegalAccessException e) {
                // 무시
            }
        }
        return fields;
    }

    @Override
    public List<String> tokenize(boolean includeTag, String delimiter) {
        List<String> tokens = new ArrayList<>();
        if (includeTag) tokens.add("[" + getTagName() + "]");
        getFields().forEach((name, value) -> tokens.add(name + delimiter + value));
        return tokens;
    }

    @Override
    public String toCustomString() {
        Map<String, String> fields = getFields();
        String opcodeHex = fields.get("format");
        String mnemonic = OpcodeTable.OPCODE_NAME_MAP.getOrDefault(
            opcodeHex.substring(0,2), "unknown"
        );

        StringBuilder sb = new StringBuilder();
        sb.append("<Start Entry>");
        sb.append("<Start>--- mnenonic:").append(mnemonic).append("<End>\n");
        sb.append("<Start>hex:").append(opcodeHex).append("<End>\n");

        fields.forEach((name, value) -> {
            if (!name.equals("format")) {
                sb.append("<Start>").append(name).append(":").append(value).append("<End>\n");
            }
        });
        sb.append("<End Entry>\n");

        return sb.toString();
    }


    @Override
    public String toString() {
        return toCustomString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
