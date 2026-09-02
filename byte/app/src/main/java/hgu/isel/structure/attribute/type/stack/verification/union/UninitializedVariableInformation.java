package hgu.isel.structure.attribute.type.stack.verification.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.stack.verification.VerificationTypeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class UninitializedVariableInformation extends BaseBytecodeStructure implements VerificationTypeInformation {
    private final byte tag = 8;
    private byte[] offset; // u2

    public UninitializedVariableInformation(byte[] offset) {
        this.offset = offset;
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }
}