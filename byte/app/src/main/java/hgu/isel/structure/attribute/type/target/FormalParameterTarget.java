package hgu.isel.structure.attribute.type.target;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class FormalParameterTarget extends BaseBytecodeStructure implements AttributeInformation{
    private byte formalParameterIndex;

    public byte getFormalParameterIndex() {
        return formalParameterIndex;
    }

    public void setFormalParameterIndex(byte formalParameterIndex) {
        this.formalParameterIndex = formalParameterIndex;
    }

    public FormalParameterTarget(byte formalParameterIndex) {
        this.formalParameterIndex = formalParameterIndex;
    }

}