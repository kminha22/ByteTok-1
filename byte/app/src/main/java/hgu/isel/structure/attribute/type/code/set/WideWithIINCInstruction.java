package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class WideWithIINCInstruction extends AbstractInstruction {
    private byte format;
    private byte wide_opcode_iinc;
    private byte local_variable_index_1;
    private byte local_variable_index_2;
    private byte immediate_value_const_1;
    private byte immediate_value_const_2;

    public WideWithIINCInstruction(byte format, byte iinc, byte indexByte1, byte indexByte2, byte constByte1, byte constByte2) {
        this.format = format;
        this.wide_opcode_iinc = iinc;
        this.local_variable_index_1 = indexByte1;
        this.local_variable_index_2 = indexByte2;
        this.immediate_value_const_1 = constByte1;
        this.immediate_value_const_2 = constByte2;
    }

}
