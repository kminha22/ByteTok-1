package hgu.isel.structure.attribute.type;

import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.local.LocalVariableTypeTableInformation;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LocalVariableTypeTable implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4
    private byte[] localVariableTypeTableLength; // u2
    private LocalVariableTypeTableInformation[] localVariableTypeTable; // localVariableTypeTableLength

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

    public byte[] getLocalVariableTypeTableLength() {
        return localVariableTypeTableLength;
    }

    public void setLocalVariableTypeTableLength(byte[] localVariableTypeTableLength) {
        this.localVariableTypeTableLength = localVariableTypeTableLength;
    }

    public LocalVariableTypeTableInformation[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public void setLocalVariableTypeTable(LocalVariableTypeTableInformation[] localVariableTypeTable) {
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public LocalVariableTypeTable(byte[] attributeNameIndex, byte[] attributeLength, byte[] localVariableTypeTableLength, LocalVariableTypeTableInformation[] localVariableTypeTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.localVariableTypeTableLength = localVariableTypeTableLength;
        this.localVariableTypeTable = localVariableTypeTable;
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

        // output.add("[Local Variable Type Table Attribute Name Index]");
        for(byte b : attributeNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Local Variable Type Table Attribute Length]");
        for(byte b : attributeLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Local Variable Type Table Attribute Table Length]");
        for(byte b : localVariableTypeTableLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(LocalVariableTypeTableInformation l : localVariableTypeTable) {
            output.addAll(l.tokenize());
        }



        return output;
    }
}
