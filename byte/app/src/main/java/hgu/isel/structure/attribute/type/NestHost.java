package hgu.isel.structure.attribute.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class NestHost extends BaseBytecodeStructure implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4
    private byte[] hostClassIndex; // u2

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

    public byte[] getHostClassIndex() {
        return hostClassIndex;
    }

    public void setHostClassIndex(byte[] hostClassIndex) {
        this.hostClassIndex = hostClassIndex;
    }

    public NestHost(byte[] attributeNameIndex, byte[] attributeLength, byte[] hostClassIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.hostClassIndex = hostClassIndex;
    }
}