package hgu.isel.structure.attribute.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.parameter.Parameter;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class MethodParameters extends BaseBytecodeStructure implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4
    private byte parametersCount;
    private Parameter[] parameters; // parametersCount

    public byte[] getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(byte[] attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public byte[] getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(byte[] attributeLength) {
        this.attributeLength = attributeLength;
    }

    public byte getParametersCount() {
        return parametersCount;
    }

    public void setParametersCount(byte parametersCount) {
        this.parametersCount = parametersCount;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public MethodParameters(byte[] attributeNameIndex, byte[] attributeLength, byte parametersCount, Parameter[] parameters) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.parametersCount = parametersCount;
        this.parameters = parameters;
    }
    
}
