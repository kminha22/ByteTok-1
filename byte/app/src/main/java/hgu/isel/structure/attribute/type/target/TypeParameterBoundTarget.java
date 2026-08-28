package hgu.isel.structure.attribute.type.target;


import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class TypeParameterBoundTarget implements TargetInformation {
    private byte typeParameterIndex;
    private byte boundIndex;

    public byte getTypeParameterIndex() {
        return typeParameterIndex;
    }

    public void setTypeParameterIndex(byte typeParameterIndex) {
        this.typeParameterIndex = typeParameterIndex;
    }

    public byte getBoundIndex() {
        return boundIndex;
    }

    public void setBoundIndex(byte boundIndex) {
        this.boundIndex = boundIndex;
    }

    public TypeParameterBoundTarget(byte typeParameterIndex, byte boundIndex) {
        this.typeParameterIndex = typeParameterIndex;
        this.boundIndex = boundIndex;
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

        // output.add("[Type Parameter Bound Target Parameter Index]");
        stringBuilder.append(String.format("%02X", typeParameterIndex));
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Type Parameter Bound Target Bound Index]");
        stringBuilder.append(String.format("%02X", boundIndex));
        output.add(stringBuilder.toString());

        return output;
    }
}
