package hgu.isel.structure.attribute.type.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ExceptionTable {
    private byte[] startPC; // u2
    private byte[] endPC; // u2
    private byte[] handlerPC; // u2
    private byte[] catchType; // u2

    public byte[] getStartPC() {
        return startPC;
    }

    public void setStartPC(byte[] startPC) {
        this.startPC = startPC;
    }

    public byte[] getEndPC() {
        return endPC;
    }

    public void setEndPC(byte[] endPC) {
        this.endPC = endPC;
    }

    public byte[] getHandlerPC() {
        return handlerPC;
    }

    public void setHandlerPC(byte[] handlerPC) {
        this.handlerPC = handlerPC;
    }

    public byte[] getCatchType() {
        return catchType;
    }

    public void setCatchType(byte[] catchType) {
        this.catchType = catchType;
    }

    public ExceptionTable(byte[] startPC, byte[] endPC, byte[] handlerPC, byte[] catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
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
        // output.add("[Exception Table]");
        for(byte b : startPC) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(byte b : endPC) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(byte b : handlerPC) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(byte b : catchType) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);


        return output;
    }
}
