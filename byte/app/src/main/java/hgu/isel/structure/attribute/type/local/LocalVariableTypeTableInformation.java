package hgu.isel.structure.attribute.type.local;

import hgu.isel.structure.BaseBytecodeStructure;
/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LocalVariableTypeTableInformation extends BaseBytecodeStructure {
    private byte[] startPC; // u2
    private byte[] length; // u2
    private byte[] nameIndex; // u2
    private byte[] signatureIndex; // u2
    private byte[] index; // u2

    public byte[] getStartPC() {
        return startPC;
    }

    public void setStartPC(byte[] startPC) {
        this.startPC = startPC;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte[] getSignatureIndex() {
        return signatureIndex;
    }

    public void setSignatureIndex(byte[] signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }

    public LocalVariableTypeTableInformation(byte[] startPC, byte[] length, byte[] nameIndex, byte[] signatureIndex, byte[] index) {
        this.startPC = startPC;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
    }
    
}