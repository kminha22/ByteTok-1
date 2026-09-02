package hgu.isel.structure.attribute.type.code.set.match;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class MatchOffsetPair extends BaseBytecodeStructure{
    private byte[] match;
    private byte[] offset;

    public MatchOffsetPair(byte[] match, byte[] offset) {
        this.match = match;
        this.offset = offset;
    }

    public byte[] getMatch() {
        return match;
    }

    public void setMatch(byte[] match) {
        this.match = match;
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }
}
