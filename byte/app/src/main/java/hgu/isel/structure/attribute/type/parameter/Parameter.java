package hgu.isel.structure.attribute.type.parameter;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Parameter extends BaseBytecodeStructure {
    private byte[] nameIndex; // u2
    private byte[] accessFlags; // u2

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte[] getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(byte[] accessFlags) {
        this.accessFlags = accessFlags;
    }

    public Parameter(byte[] nameIndex, byte[] accessFlags) {
        this.nameIndex = nameIndex;
        this.accessFlags = accessFlags;
    }
   
}