package hgu.isel.structure.attribute.type.annotation.elemet.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class EnumConstValue extends BaseBytecodeStructure implements ElementUnion {
    private byte[] typeNameIndex; // u2
    private byte[] constNameIndex; // u2

    public byte[] getTypeNameIndex() {
        return typeNameIndex;
    }

    public void setTypeNameIndex(byte[] typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public byte[] getConstNameIndex() {
        return constNameIndex;
    }

    public void setConstNameIndex(byte[] constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    public EnumConstValue(byte[] typeNameIndex, byte[] constNameIndex) {
        this.typeNameIndex = typeNameIndex;
        this.constNameIndex = constNameIndex;
    }

}