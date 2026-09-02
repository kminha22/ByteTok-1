package hgu.isel.structure.attribute.type.module.open;

import hgu.isel.structure.BaseBytecodeStructure;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class OpenIndex extends BaseBytecodeStructure {
    private byte[] opensToIndex; // u2

    public byte[] getOpensToIndex() {
        return opensToIndex;
    }

    public void setOpensToIndex(byte[] opensToIndex) {
        this.opensToIndex = opensToIndex;
    }

    public OpenIndex(byte[] opensToIndex) {
        this.opensToIndex = opensToIndex;
    }
   
}