package hgu.isel.structure.attribute.type.stack.verification.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.stack.verification.VerificationTypeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ObjectVariableInformation extends BaseBytecodeStructure implements VerificationTypeInformation {
    private final byte tag = 7;
    private byte[] constantPoolIndex; // u2

    public ObjectVariableInformation(byte[] constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }

    public byte getTag() {
        return tag;
    }

    public byte[] getConstantPoolIndex() {
        return constantPoolIndex;
    }

    public void setConstantPoolIndex(byte[] constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }
}