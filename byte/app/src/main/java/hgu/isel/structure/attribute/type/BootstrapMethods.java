package hgu.isel.structure.attribute.type;

import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.boot.BootstrapMethodInformation;

import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class BootstrapMethods implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4
    private byte[] numberOfBootstrapMethods; // u2
    private BootstrapMethodInformation[] bootstrapMethods; // numberOfBootstrapMethods

    public byte[] getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(byte[] attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public byte[] getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(byte[] attributeLength) {
        this.attributeLength = attributeLength;
    }

    public byte[] getNumberOfBootstrapMethods() {
        return numberOfBootstrapMethods;
    }

    public void setNumberOfBootstrapMethods(byte[] numberOfBootstrapMethods) {
        this.numberOfBootstrapMethods = numberOfBootstrapMethods;
    }

    public BootstrapMethodInformation[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public void setBootstrapMethods(BootstrapMethodInformation[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(byte[] attributeNameIndex, byte[] attributeLength, byte[] numberOfBootstrapMethods, BootstrapMethodInformation[] bootstrapMethods) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.numberOfBootstrapMethods = numberOfBootstrapMethods;
        this.bootstrapMethods = bootstrapMethods;
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

        // output.add("[Bootstrap Method Attribute Name Index]");
        for(byte b : attributeNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Bootstrap Method Attribute Length]");
        for(byte b : attributeLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Bootstrap Method Attribute Number]");
        for(byte b : numberOfBootstrapMethods) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(BootstrapMethodInformation b : bootstrapMethods) {
            for(String s : b.tokenize()) {
                // output.add("[Bootstrap Method Attribute Methods]");
                stringBuilder.append(s);
                output.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }


        return output;
    }
}
