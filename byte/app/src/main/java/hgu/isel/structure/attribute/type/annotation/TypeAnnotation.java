package hgu.isel.structure.attribute.type.annotation;

import hgu.isel.structure.attribute.type.path.TypePath;
import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class TypeAnnotation extends BaseBytecodeStructure {
    private byte targetType;
    private AttributeInformation targetInformation;
    private TypePath targetPath;
    private byte[] typeIndex; // u2
    private byte[] numberOfElementValuePairs; // u2
    private ElementValuePairs[] elementValuePairs; // numberOfElementValuePairs

    public byte getTargetType() {
        return targetType;
    }

    public void setTargetType(byte targetType) {
        this.targetType = targetType;
    }

    public AttributeInformation getTargetInformation() {
        return targetInformation;
    }

    public void setTargetInformation(AttributeInformation targetInformation) {
        this.targetInformation = targetInformation;
    }

    public TypePath getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(TypePath targetPath) {
        this.targetPath = targetPath;
    }

    public byte[] getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(byte[] typeIndex) {
        this.typeIndex = typeIndex;
    }

    public byte[] getNumberOfElementValuePairs() {
        return numberOfElementValuePairs;
    }

    public void setNumberOfElementValuePairs(byte[] numberOfElementValuePairs) {
        this.numberOfElementValuePairs = numberOfElementValuePairs;
    }

    public ElementValuePairs[] getElementValuePairs() {
        return elementValuePairs;
    }

    public void setElementValuePairs(ElementValuePairs[] elementValuePairs) {
        this.elementValuePairs = elementValuePairs;
    }

    public TypeAnnotation(byte targetType, AttributeInformation targetInformation, TypePath targetPath, byte[] typeIndex, byte[] numberOfElementValuePairs, ElementValuePairs[] elementValuePairs) {
        this.targetType = targetType;
        this.targetInformation = targetInformation;
        this.targetPath = targetPath;
        this.typeIndex = typeIndex;
        this.numberOfElementValuePairs = numberOfElementValuePairs;
        this.elementValuePairs = elementValuePairs;
    }
}