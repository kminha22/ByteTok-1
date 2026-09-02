package hgu.isel.structure.attribute.type.stack.verification.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.stack.verification.VerificationTypeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class DoubleVariableInformation extends BaseBytecodeStructure implements VerificationTypeInformation {
    private final byte tag = 3;

    public DoubleVariableInformation() {
    }
}