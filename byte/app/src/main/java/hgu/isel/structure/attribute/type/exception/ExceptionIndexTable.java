package hgu.isel.structure.attribute.type.exception;

import hgu.isel.structure.BaseBytecodeStructure;
/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ExceptionIndexTable extends BaseBytecodeStructure {
    private byte[] exceptionIndexTable; // u2

    public byte[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public void setExceptionIndexTable(byte[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }

    public ExceptionIndexTable(byte[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }
    
}