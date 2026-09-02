package hgu.isel.structure.attribute.type.stack.frame.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.stack.frame.StackMapFrame;
import hgu.isel.structure.attribute.type.stack.verification.VerificationTypeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class SameLocals1StackItemFrame extends BaseBytecodeStructure implements StackMapFrame {
    private byte frameType; // 64 - 127
    private VerificationTypeInformation stack; // 1

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }

    public VerificationTypeInformation getStack() {
        return stack;
    }

    public void setStack(VerificationTypeInformation stack) {
        this.stack = stack;
    }

    public SameLocals1StackItemFrame(byte frameType, VerificationTypeInformation stack) {
        this.frameType = frameType;
        this.stack = stack;
    }
}