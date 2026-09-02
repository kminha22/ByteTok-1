package hgu.isel.structure.constant.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.constant.ConstantPoolInformation;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class NameAndTypeInformation extends BaseBytecodeStructure implements ConstantPoolInformation {
    private byte tag;
    private byte[] nameIndex; // u2
    private byte[] descriptorIndex; // u2

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte[] getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(byte[] descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public NameAndTypeInformation(byte tag, byte[] nameIndex, byte[] descriptorIndex) {
        this.tag = tag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

}
