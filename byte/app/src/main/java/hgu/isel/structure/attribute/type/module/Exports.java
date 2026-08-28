package hgu.isel.structure.attribute.type.module;

import hgu.isel.structure.attribute.type.module.export.ExportIndex;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Exports {
    private byte[] exportsIndex; // u2
    private byte[] exportsFlags; // u2
    private byte[] exportsToCount; // u2
    private ExportIndex[] exportsToIndex; // numberOfExportsToCount

    public byte[] getExportsIndex() {
        return exportsIndex;
    }

    public void setExportsIndex(byte[] exportsIndex) {
        this.exportsIndex = exportsIndex;
    }

    public byte[] getExportsFlags() {
        return exportsFlags;
    }

    public void setExportsFlags(byte[] exportsFlags) {
        this.exportsFlags = exportsFlags;
    }

    public byte[] getExportsToCount() {
        return exportsToCount;
    }

    public void setExportsToCount(byte[] exportsToCount) {
        this.exportsToCount = exportsToCount;
    }

    public ExportIndex[] getExportsToIndex() {
        return exportsToIndex;
    }

    public void setExportsToIndex(ExportIndex[] exportsToIndex) {
        this.exportsToIndex = exportsToIndex;
    }

    public Exports(byte[] exportsIndex, byte[] exportsFlags, byte[] exportsToCount, ExportIndex[] exportsToIndex) {
        this.exportsIndex = exportsIndex;
        this.exportsFlags = exportsFlags;
        this.exportsToCount = exportsToCount;
        this.exportsToIndex = exportsToIndex;
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

        // output.add("[Exports Index]");
        for(byte b : exportsIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Exports Flag]");
        for(byte b : exportsFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Exports Count]");
        for(byte b : exportsToCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(ExportIndex e : exportsToIndex) {
            output.addAll(e.tokenize());
        }

        return output;
    }
}
