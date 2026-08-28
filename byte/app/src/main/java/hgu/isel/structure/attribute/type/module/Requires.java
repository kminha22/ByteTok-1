package hgu.isel.structure.attribute.type.module;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Requires {
    private byte[] requiresIndex; // u2
    private byte[] requiresFlags; // u2
    private byte[] requiresVersionIndex; // u2

    public byte[] getRequiresIndex() {
        return requiresIndex;
    }

    public void setRequiresIndex(byte[] requiresIndex) {
        this.requiresIndex = requiresIndex;
    }

    public byte[] getRequiresFlags() {
        return requiresFlags;
    }

    public void setRequiresFlags(byte[] requiresFlags) {
        this.requiresFlags = requiresFlags;
    }

    public byte[] getRequiresVersionIndex() {
        return requiresVersionIndex;
    }

    public void setRequiresVersionIndex(byte[] requiresVersionIndex) {
        this.requiresVersionIndex = requiresVersionIndex;
    }

    public Requires(byte[] requiresIndex, byte[] requiresFlags, byte[] requiresVersionIndex) {
        this.requiresIndex = requiresIndex;
        this.requiresFlags = requiresFlags;
        this.requiresVersionIndex = requiresVersionIndex;
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

    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        // output.add("[Requires Index]");
        for(byte b : requiresIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Requires Flag]");
        for(byte b : requiresFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Requires Version Index]");
        for(byte b : requiresVersionIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        return output;
    }
}
