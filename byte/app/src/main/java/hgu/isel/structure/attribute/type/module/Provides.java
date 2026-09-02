package hgu.isel.structure.attribute.type.module;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.module.provide.ProvidesIndex;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Provides extends BaseBytecodeStructure {
    private byte[] providesIndex; // u2
    private byte[] providesWithCount; // u2
    private ProvidesIndex[] providesWithIndex; // u2

    public byte[] getProvidesIndex() {
        return providesIndex;
    }

    public void setProvidesIndex(byte[] providesIndex) {
        this.providesIndex = providesIndex;
    }

    public byte[] getProvidesWithCount() {
        return providesWithCount;
    }

    public void setProvidesWithCount(byte[] providesWithCount) {
        this.providesWithCount = providesWithCount;
    }

    public ProvidesIndex[] getProvidesWithIndex() {
        return providesWithIndex;
    }

    public void setProvidesWithIndex(ProvidesIndex[] providesWithIndex) {
        this.providesWithIndex = providesWithIndex;
    }

    public Provides(byte[] providesIndex, byte[] providesWithCount, ProvidesIndex[] providesWithIndex) {
        this.providesIndex = providesIndex;
        this.providesWithCount = providesWithCount;
        this.providesWithIndex = providesWithIndex;
    }

}