package hgu.isel.structure.attribute.type;

import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.code.CodeAttributeAnalyzer;
import hgu.isel.structure.attribute.type.code.Instruction;
import hgu.isel.structure.attribute.type.exception.ExceptionTable;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Code implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4 563
    private byte[] maxStack; // u2
    private byte[] maxLocals; // u2
    private byte[] codeLength; // u4
    private ArrayList<Instruction> code; // codeLength
    private byte[] exceptionTableLength; // u2
    private ExceptionTable[] exceptionTable; // exceptionTableLength
    private byte[] attributesCount; // u2
    private AttributeInformation[] attributes; // attributesCount

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

    public byte[] getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(byte[] maxStack) {
        this.maxStack = maxStack;
    }

    public byte[] getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(byte[] maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(byte[] codeLength) {
        this.codeLength = codeLength;
    }

    public ArrayList<Instruction> getCode() {
        return code;
    }

    public void setCode(ArrayList<Instruction> code) {
        this.code = code;
    }

    public byte[] getExceptionTableLength() {
        return exceptionTableLength;
    }

    public void setExceptionTableLength(byte[] exceptionTableLength) {
        this.exceptionTableLength = exceptionTableLength;
    }

    public ExceptionTable[] getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(ExceptionTable[] exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public byte[] getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(byte[] attributesCount) {
        this.attributesCount = attributesCount;
    }

    public AttributeInformation[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInformation[] attributes) {
        this.attributes = attributes;
    }

    public Code(byte[] attributeNameIndex, byte[] attributeLength, byte[] maxStack, byte[] maxLocals, byte[] codeLength, byte[] code, byte[] exceptionTableLength, ExceptionTable[] exceptionTable, byte[] attributesCount, AttributeInformation[] attributes, int totalOffset) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLength = codeLength;

        this.exceptionTableLength = exceptionTableLength;
        this.exceptionTable = exceptionTable;
        this.attributesCount = attributesCount;
        this.attributes = attributes;

        CodeAttributeAnalyzer codeAttributeAnalyzer = new CodeAttributeAnalyzer(code, totalOffset);

        codeAttributeAnalyzer.analyze();

        this.code = codeAttributeAnalyzer.getInstructions();
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

        // output.add("[Code Attribute Name Index]");
        for(byte b : attributeNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Code Attribute Length]");
        for(byte b : attributeLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Code Attribute Max Stack]");
        for(byte b : maxStack) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Code Attribute Max Locals]");
        for(byte b : maxLocals) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Code Attribute Code Length]");
        for(byte b : codeLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(Instruction i : code) {
            output.addAll(i.tokenize(false, " = "));
        }

        // output.add("[Code Attribute Exception Table Length]");
        for(byte b : exceptionTableLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(ExceptionTable i : exceptionTable) {
            output.addAll(i.tokenize());
        }

        // output.add("[Code Attribute Count]");
        for(byte b : attributesCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(AttributeInformation i : attributes) {
            output.addAll(i.tokenize());
        }


        return output;
    }

}
