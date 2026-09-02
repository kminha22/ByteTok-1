package hgu.isel.structure.attribute.type.target;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class OffsetTarget extends BaseBytecodeStructure implements AttributeInformation{
    private byte[] offset; // u2

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }

    public OffsetTarget(byte[] offset) {
        this.offset = offset;
    }
    
}