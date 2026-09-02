package hgu.isel.structure.attribute.type.line;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LineNumberTableInformation extends BaseBytecodeStructure {
    private byte[] startPC; // u2
    private byte[] lineNumber; // u2

    public byte[] getStartPC() {
        return startPC;
    }

    public void setStartPC(byte[] startPC) {
        this.startPC = startPC;
    }

    public byte[] getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(byte[] lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LineNumberTableInformation(byte[] startPC, byte[] lineNumber) {
        this.startPC = startPC;
        this.lineNumber = lineNumber;
    }

}