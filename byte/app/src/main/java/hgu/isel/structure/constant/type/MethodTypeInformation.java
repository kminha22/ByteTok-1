package hgu.isel.structure.constant.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.constant.ConstantPoolInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class MethodTypeInformation extends BaseBytecodeStructure implements ConstantPoolInformation {
    private byte tag;
    private byte[] descriptorIndex; // u2

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(byte[] descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public MethodTypeInformation(byte tag, byte[] descriptorIndex) {
        this.tag = tag;
        this.descriptorIndex = descriptorIndex;
    }
}
