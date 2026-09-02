package hgu.isel.structure.attribute.type.module.uses;

import hgu.isel.structure.BaseBytecodeStructure;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class UsesIndex extends BaseBytecodeStructure {
    private byte[] usesIndex; // u2

    public byte[] getUsesIndex() {
        return usesIndex;
    }

    public void setUsesIndex(byte[] usesIndex) {
        this.usesIndex = usesIndex;
    }

    public UsesIndex(byte[] usesIndex) {
        this.usesIndex = usesIndex;
    }
    
}