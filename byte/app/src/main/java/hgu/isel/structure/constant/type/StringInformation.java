package hgu.isel.structure.constant.type;


import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.constant.ConstantPoolInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class StringInformation extends BaseBytecodeStructure implements ConstantPoolInformation {
    private byte tag;
    private byte[] stringIndex; // u2

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(byte[] stringIndex) {
        this.stringIndex = stringIndex;
    }

    public StringInformation(byte tag, byte[] stringIndex) {
        this.tag = tag;
        this.stringIndex = stringIndex;
    }
    
}
