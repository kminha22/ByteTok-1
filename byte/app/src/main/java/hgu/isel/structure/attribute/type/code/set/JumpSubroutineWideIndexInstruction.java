package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class JumpSubroutineWideIndexInstruction extends AbstractInstruction {
    private byte format;
    private byte jump_offset_1;
    private byte jump_offset_2;
    private byte jump_offset_3;
    private byte jump_offset_4;

    public JumpSubroutineWideIndexInstruction(byte format, byte branchByte1, byte branchByte2, byte branchByte3, byte branchByte4) {
        this.format = format;
        this.jump_offset_1 = branchByte1;
        this.jump_offset_2 = branchByte2;
        this.jump_offset_3 = branchByte3;
        this.jump_offset_4 = branchByte4;
    }

}