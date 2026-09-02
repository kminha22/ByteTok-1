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
public class FullFrame extends BaseBytecodeStructure implements StackMapFrame {
    private byte frameType; // 255
    private byte[] offsetDelta; // u2
    private byte[] numberOfLocals; // u2
    private VerificationTypeInformation[] locals; // numberOfLocals
    private byte[] numberOfStackItems; // u2
    private VerificationTypeInformation[] stack; // numberOfStackItems

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

    public byte[] getNumberOfLocals() {
        return numberOfLocals;
    }

    public void setNumberOfLocals(byte[] numberOfLocals) {
        this.numberOfLocals = numberOfLocals;
    }

    public VerificationTypeInformation[] getLocals() {
        return locals;
    }

    public void setLocals(VerificationTypeInformation[] locals) {
        this.locals = locals;
    }

    public byte[] getNumberOfStackItems() {
        return numberOfStackItems;
    }

    public void setNumberOfStackItems(byte[] numberOfStackItems) {
        this.numberOfStackItems = numberOfStackItems;
    }

    public VerificationTypeInformation[] getStack() {
        return stack;
    }

    public void setStack(VerificationTypeInformation[] stack) {
        this.stack = stack;
    }

    public FullFrame(byte frameType, byte[] offsetDelta, byte[] numberOfLocals, VerificationTypeInformation[] locals, byte[] numberOfStackItems, VerificationTypeInformation[] stack) {
        this.frameType = frameType;
        this.offsetDelta = offsetDelta;
        this.numberOfLocals = numberOfLocals;
        this.locals = locals;
        this.numberOfStackItems = numberOfStackItems;
        this.stack = stack;
    }
    
}