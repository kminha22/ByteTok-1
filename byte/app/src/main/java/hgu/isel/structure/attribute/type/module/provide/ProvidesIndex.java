package hgu.isel.structure.attribute.type.module.provide;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ProvidesIndex extends BaseBytecodeStructure {
    private byte[] providesIndex;

    public byte[] getProvidesIndex() {
        return providesIndex;
    }

    public void setProvidesIndex(byte[] providesIndex) {
        this.providesIndex = providesIndex;
    }

    public ProvidesIndex(byte[] providesIndex) {
        this.providesIndex = providesIndex;
    }
   
}