package hgu.isel.structure.attribute.type.annotation.elemet.union;

import hgu.isel.structure.attribute.type.annotation.ElementValuePairs;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Annotation implements ElementUnion {
    private byte[] typeIndex; // u2
    private byte[] numberOfElementValuePairs; // u2
    private ElementValuePairs[] elementValuePairs; // numberOfElementValuePairs

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

    public Annotation(byte[] typeIndex, byte[] numberOfElementValuePairs, ElementValuePairs[] elementValuePairs) {
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

    @Override
    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();


        // output.add("[Annotation Type Index]");

        for(byte b : typeIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Annotation Element Value Pairs Number]");
        for(byte b : numberOfElementValuePairs) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());

        for(ElementValuePairs e : elementValuePairs) {
            for(String s : e.tokenize()) {
                // output.add("[Annotation Element Value Pairs]");
                stringBuilder.append(s);
                output.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }

        return output;
    }
}
