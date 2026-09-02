package hgu.isel.structure.attribute.type.exception;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ExceptionTable extends BaseBytecodeStructure {
    private byte[] startPC; // u2
    private byte[] endPC; // u2
    private byte[] handlerPC; // u2
    private byte[] catchType; // u2

    public byte[] getStartPC() {
        return startPC;
    }

    public void setStartPC(byte[] startPC) {
        this.startPC = startPC;
    }

    public byte[] getEndPC() {
        return endPC;
    }

    public void setEndPC(byte[] endPC) {
        this.endPC = endPC;
    }

    public byte[] getHandlerPC() {
        return handlerPC;
    }

    public void setHandlerPC(byte[] handlerPC) {
        this.handlerPC = handlerPC;
    }

    public byte[] getCatchType() {
        return catchType;
    }

    public void setCatchType(byte[] catchType) {
        this.catchType = catchType;
    }

    public ExceptionTable(byte[] startPC, byte[] endPC, byte[] handlerPC, byte[] catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
    }

}