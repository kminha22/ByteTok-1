package hgu.isel.structure.attribute.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.code.CodeAttributeAnalyzer;
import hgu.isel.structure.attribute.type.code.Instruction;
import hgu.isel.structure.attribute.type.exception.ExceptionTable;

import java.util.ArrayList;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Code extends BaseBytecodeStructure implements AttributeInformation {
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
    
}
