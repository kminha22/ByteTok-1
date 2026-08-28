package hgu.isel.structure.attribute.type.stack.verification.union;

import hgu.isel.structure.attribute.type.stack.verification.VerificationTypeInformation;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class NullVariableInformation implements VerificationTypeInformation {
    private final byte tag = 5;

    public NullVariableInformation() {
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Start Entry>");
        sb.append("<Start>--- Type:").append(getClass().getSimpleName()).append("<End>\n");

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                sb.append("<Start>")
                .append(field.getName())
                .append(":");

                if (value instanceof Byte) {
                    sb.append(String.format("%02X", value));
                } else if (value instanceof byte[]) {
                    sb.append(bytesToHex((byte[]) value));
                } else if (value.getClass().isArray()) {
                    // 배열 처리
                    Object[] arr = (Object[]) value;
                    sb.append("[");
                    for (int i = 0; i < arr.length; i++) {
                        Object elem = arr[i];
                        if (elem != null) {
                            sb.append(elem.toString()); // 각 객체의 toString() 호출
                        } else {
                            sb.append("null");
                        }
                    }
                    sb.append("]");
                } else {
                    sb.append(value.toString());
                }

                sb.append("<End>");
            } catch (IllegalAccessException e) {
                // 무시
            }
        }

        sb.append("<End Entry>");
        return sb.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }

    @Override
    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        // output.add("[Null Variable Verification]");
        stringBuilder.append(String.format("%02X", tag));
        output.add(stringBuilder.toString());

        return output;
    }
}
