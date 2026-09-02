package hgu.isel.structure.attribute.type.stack.frame.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.stack.frame.StackMapFrame;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ChopFrame extends BaseBytecodeStructure implements StackMapFrame {
    private byte frameType; // 248 - 250
    private byte[] offsetDelta; // u2

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }

    public byte[] getOffsetDelta() {
        return offsetDelta;
    }

    public void setOffsetDelta(byte[] offsetDelta) {
        this.offsetDelta = offsetDelta;
    }

    public ChopFrame(byte frameType, byte[] offsetDelta) {
        this.frameType = frameType;
        this.offsetDelta = offsetDelta;
    }
    
}