package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;
import hgu.isel.structure.attribute.type.code.set.jump.JumpOffset;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class TableSwitchInstruction extends AbstractInstruction {
    private byte format;
    private byte[] padding;
    private byte[] default_jump_offset;
    private byte[] low_bound;
    private byte[] high_bound;
    private JumpOffset[] jump_table_offsets;

    public TableSwitchInstruction(byte format, byte[] paddingBytes, byte[] defaultByte, byte[] lowBytes, byte[] highBytes, JumpOffset[] jumpOffsets) {
        this.format = format;
        this.padding = paddingBytes;
        this.default_jump_offset = defaultByte;
        this.low_bound = lowBytes;
        this.high_bound = highBytes;
        this.jump_table_offsets = jumpOffsets;
    }


}
