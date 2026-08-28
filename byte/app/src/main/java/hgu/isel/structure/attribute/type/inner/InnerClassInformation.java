package hgu.isel.structure.attribute.type.inner;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class InnerClassInformation {
    private byte[] innerClassInformationIndex; // u2
    private byte[] outerClassInformationIndex; // u2
    private byte[] innerNameIndex; // u2
    private byte[] innerClassAccessFlags; // u2

    public byte[] getInnerClassInformationIndex() {
        return innerClassInformationIndex;
    }

    public void setInnerClassInformationIndex(byte[] innerClassInformationIndex) {
        this.innerClassInformationIndex = innerClassInformationIndex;
    }

    public byte[] getOuterClassInformationIndex() {
        return outerClassInformationIndex;
    }

    public void setOuterClassInformationIndex(byte[] outerClassInformationIndex) {
        this.outerClassInformationIndex = outerClassInformationIndex;
    }

    public byte[] getInnerNameIndex() {
        return innerNameIndex;
    }

    public void setInnerNameIndex(byte[] innerNameIndex) {
        this.innerNameIndex = innerNameIndex;
    }

    public byte[] getInnerClassAccessFlags() {
        return innerClassAccessFlags;
    }

    public void setInnerClassAccessFlags(byte[] innerClassAccessFlags) {
        this.innerClassAccessFlags = innerClassAccessFlags;
    }

    public InnerClassInformation(byte[] innerClassInformationIndex, byte[] outerClassInformationIndex, byte[] innerNameIndex, byte[] innerClassAccessFlags) {
        this.innerClassInformationIndex = innerClassInformationIndex;
        this.outerClassInformationIndex = outerClassInformationIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerClassAccessFlags = innerClassAccessFlags;
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

        // output.add("[Inner Class Information Index]");
        for(byte b : innerClassInformationIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Outer Class Information Index]");
        for(byte b : outerClassInformationIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Inner Class Name Index]");
        for(byte b : innerNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Inner Class Access Flag]");
        for(byte b : innerClassAccessFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);


        return output;
    }
}
