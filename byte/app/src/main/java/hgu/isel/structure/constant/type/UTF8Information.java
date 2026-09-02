package hgu.isel.structure.constant.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.constant.ConstantPoolInformation;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class UTF8Information extends BaseBytecodeStructure implements ConstantPoolInformation {
    private byte tag;
    private byte[] length; // u2
    private byte[] bytes; // length

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public UTF8Information(byte tag, byte[] length, byte[] bytes) {
        this.tag = tag;
        this.length = length;
        this.bytes = bytes;
    }
  
}
