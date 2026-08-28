package hgu.isel.structure.attribute.type.target;


import hgu.isel.structure.attribute.type.target.local.LocalVariableTargetTable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LocalVariableTarget implements TargetInformation{
    private byte[] tableLength; // u2
    private LocalVariableTargetTable[] table;

    public byte[] getTableLength() {
        return tableLength;
    }

    public void setTableLength(byte[] tableLength) {
        this.tableLength = tableLength;
    }

    public LocalVariableTargetTable[] getTable() {
        return table;
    }

    public void setTable(LocalVariableTargetTable[] table) {
        this.table = table;
    }

    public LocalVariableTarget(byte[] tableLength, LocalVariableTargetTable[] table) {
        this.tableLength = tableLength;
        this.table = table;
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

        // output.add("[Local Variable Target Table Length]");
        for(byte b : tableLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(LocalVariableTargetTable c : table) {
            output.addAll(c.tokenize());
        }

        return output;
    }
}
