package hgu.isel.structure.attribute.type.module;

import hgu.isel.structure.attribute.type.module.provide.ProvidesIndex;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Provides {
    private byte[] providesIndex; // u2
    private byte[] providesWithCount; // u2
    private ProvidesIndex[] providesWithIndex; // u2

    public byte[] getProvidesIndex() {
        return providesIndex;
    }

    public void setProvidesIndex(byte[] providesIndex) {
        this.providesIndex = providesIndex;
    }

    public byte[] getProvidesWithCount() {
        return providesWithCount;
    }

    public void setProvidesWithCount(byte[] providesWithCount) {
        this.providesWithCount = providesWithCount;
    }

    public ProvidesIndex[] getProvidesWithIndex() {
        return providesWithIndex;
    }

    public void setProvidesWithIndex(ProvidesIndex[] providesWithIndex) {
        this.providesWithIndex = providesWithIndex;
    }

    public Provides(byte[] providesIndex, byte[] providesWithCount, ProvidesIndex[] providesWithIndex) {
        this.providesIndex = providesIndex;
        this.providesWithCount = providesWithCount;
        this.providesWithIndex = providesWithIndex;
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

        // output.add("[Provides Index]");
        for(byte b : providesIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Provides With Count]");
        for(byte b : providesWithCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(ProvidesIndex p : providesWithIndex) {
            output.addAll(p.tokenize());
        }

        return output;
    }
}
