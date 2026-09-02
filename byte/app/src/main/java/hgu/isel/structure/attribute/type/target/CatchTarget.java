package hgu.isel.structure.attribute.type.target;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class CatchTarget extends BaseBytecodeStructure implements AttributeInformation{
    private byte[] exceptionTableIndex; // u2

    public byte[] getExceptionTableIndex() {
        return exceptionTableIndex;
    }

    public void setExceptionTableIndex(byte[] exceptionTableIndex) {
        this.exceptionTableIndex = exceptionTableIndex;
    }

    public CatchTarget(byte[] exceptionTableIndex) {
        this.exceptionTableIndex = exceptionTableIndex;
    }
    
}