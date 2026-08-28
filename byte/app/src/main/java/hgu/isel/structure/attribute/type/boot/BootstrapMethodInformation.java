package hgu.isel.structure.attribute.type.boot;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class BootstrapMethodInformation {
    private byte[] bootstrapMethodReference; // u2
    private byte[] numberOfBootstrapArguments; // u2
    private BootstrapArgument[] bootstrapArguments; // numberOfBootstrapArguments

    public byte[] getBootstrapMethodReference() {
        return bootstrapMethodReference;
    }

    public void setBootstrapMethodReference(byte[] bootstrapMethodReference) {
        this.bootstrapMethodReference = bootstrapMethodReference;
    }

    public byte[] getNumberOfBootstrapArguments() {
        return numberOfBootstrapArguments;
    }

    public void setNumberOfBootstrapArguments(byte[] numberOfBootstrapArguments) {
        this.numberOfBootstrapArguments = numberOfBootstrapArguments;
    }

    public BootstrapArgument[] getBootstrapArguments() {
        return bootstrapArguments;
    }

    public void setBootstrapArguments(BootstrapArgument[] bootstrapArguments) {
        this.bootstrapArguments = bootstrapArguments;
    }

    public BootstrapMethodInformation(byte[] bootstrapMethodReference, byte[] numberOfBootstrapArguments, BootstrapArgument[] bootstrapArguments) {
        this.bootstrapMethodReference = bootstrapMethodReference;
        this.numberOfBootstrapArguments = numberOfBootstrapArguments;
        this.bootstrapArguments = bootstrapArguments;
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


        for(byte b : bootstrapMethodReference) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(byte b : numberOfBootstrapArguments) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());

        for(BootstrapArgument b : bootstrapArguments) {
            output.addAll(b.tokenize());
        }


        return output;
    }
}
