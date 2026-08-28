package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;
import hgu.isel.structure.attribute.type.code.set.match.MatchOffsetPair;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LookUpSwitchInstruction extends AbstractInstruction {
    private byte format;
    private byte[] padding; // 0x00으로 채워짐
    // format이 4바이트 경계에 위치하도록 해야함
    private byte[] default_jump_offset;
    private byte[] immediate_value_paircount;
    private MatchOffsetPair[] jump_offset_pairs; // nPairs의 수에 따라 offset pairs의 수가 결정됨

    public LookUpSwitchInstruction(byte format, byte[] paddingByte, byte[] defaultBytes, byte[] nPairs, MatchOffsetPair[] matchOffsetPairs) {
        this.format = format;
        this.padding = paddingByte;
        this.default_jump_offset = defaultBytes;
        this.immediate_value_paircount = nPairs;
        this.jump_offset_pairs = matchOffsetPairs;
    }

}
