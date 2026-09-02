package hgu.isel.structure.attribute.type.annotation.elemet.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ConstValueIndex extends BaseBytecodeStructure implements ElementUnion {
    private byte[] constValueIndex;

    public byte[] getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(byte[] constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public ConstValueIndex(byte[] constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

}