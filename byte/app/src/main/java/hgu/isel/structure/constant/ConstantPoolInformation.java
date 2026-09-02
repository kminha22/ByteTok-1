package hgu.isel.structure.constant;

import java.util.Map;

import hgu.isel.structure.JsonSerializable;

import java.util.LinkedHashMap;
import java.lang.reflect.Field;


public interface ConstantPoolInformation extends JsonSerializable{

    default public Map<String, String> getFields() {
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

    default public String toCustomString() {
        Map<String, String> fields = getFields();
        StringBuilder sb = new StringBuilder();
        sb.append("<Start Entry>");

        sb.append("<Start>--- Type:").append(getClass().getSimpleName()).append("<End>\n");
        fields.forEach((name, value) -> {    
            sb.append("<Start>").append(name).append(":").append(value).append("<End>\n");
        });
        sb.append("<End Entry>\n");

        return sb.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }
    
}
