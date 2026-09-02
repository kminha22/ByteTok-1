package hgu.isel.structure.attribute.type.module;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.module.open.OpenIndex;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Opens extends BaseBytecodeStructure {
    private byte[] opensIndex; // u2
    private byte[] opensFlags; // u2
    private byte[] opensToCount; // u2
    private OpenIndex[] opensToIndex; // opensToCount

    public byte[] getOpensIndex() {
        return opensIndex;
    }

    public void setOpensIndex(byte[] opensIndex) {
        this.opensIndex = opensIndex;
    }

    public byte[] getOpensFlags() {
        return opensFlags;
    }

    public void setOpensFlags(byte[] opensFlags) {
        this.opensFlags = opensFlags;
    }

    public byte[] getOpensToCount() {
        return opensToCount;
    }

    public void setOpensToCount(byte[] opensToCount) {
        this.opensToCount = opensToCount;
    }

    public OpenIndex[] getOpensToIndex() {
        return opensToIndex;
    }

    public void setOpensToIndex(OpenIndex[] opensToIndex) {
        this.opensToIndex = opensToIndex;
    }

    public Opens(byte[] opensIndex, byte[] opensFlags, byte[] opensToCount, OpenIndex[] opensToIndex) {
        this.opensIndex = opensIndex;
        this.opensFlags = opensFlags;
        this.opensToCount = opensToCount;
        this.opensToIndex = opensToIndex;
    }
    
}