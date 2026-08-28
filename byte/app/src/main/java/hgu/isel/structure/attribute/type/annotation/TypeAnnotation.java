package hgu.isel.structure.attribute.type.annotation;

import hgu.isel.structure.attribute.type.path.TypePath;
import hgu.isel.structure.attribute.type.target.TargetInformation;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class TypeAnnotation {
    private byte targetType;
    private TargetInformation targetInformation;
    private TypePath targetPath;
    private byte[] typeIndex; // u2
    private byte[] numberOfElementValuePairs; // u2
    private ElementValuePairs[] elementValuePairs; // numberOfElementValuePairs

    public byte getTargetType() {
        return targetType;
    }

    public void setTargetType(byte targetType) {
        this.targetType = targetType;
    }

    public TargetInformation getTargetInformation() {
        return targetInformation;
    }

    public void setTargetInformation(TargetInformation targetInformation) {
        this.targetInformation = targetInformation;
    }

    public TypePath getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(TypePath targetPath) {
        this.targetPath = targetPath;
    }

    public byte[] getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(byte[] typeIndex) {
        this.typeIndex = typeIndex;
    }

    public byte[] getNumberOfElementValuePairs() {
        return numberOfElementValuePairs;
    }

    public void setNumberOfElementValuePairs(byte[] numberOfElementValuePairs) {
        this.numberOfElementValuePairs = numberOfElementValuePairs;
    }

    public ElementValuePairs[] getElementValuePairs() {
        return elementValuePairs;
    }

    public void setElementValuePairs(ElementValuePairs[] elementValuePairs) {
        this.elementValuePairs = elementValuePairs;
    }

    public TypeAnnotation(byte targetType, TargetInformation targetInformation, TypePath targetPath, byte[] typeIndex, byte[] numberOfElementValuePairs, ElementValuePairs[] elementValuePairs) {
        this.targetType = targetType;
        this.targetInformation = targetInformation;
        this.targetPath = targetPath;
        this.typeIndex = typeIndex;
        this.numberOfElementValuePairs = numberOfElementValuePairs;
        this.elementValuePairs = elementValuePairs;
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
                        if (i < arr.length - 1) sb.append(", ");
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
        // output.add("[Type Annotation Type]");
        stringBuilder.append(String.format("%02X", targetType));
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        output.addAll(targetInformation.tokenize());
        output.addAll(targetPath.tokenize());

        // output.add("[Type Annotation Type Index]");
        for(byte b : typeIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Type Annotation Element Number]");
        for(byte b : numberOfElementValuePairs) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(ElementValuePairs c : elementValuePairs) {
            output.addAll(c.tokenize());
        }

        return output;
    }
}
