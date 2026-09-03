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
    private byte wide_opcode;
    private byte[] local_variable;
    private byte[] immediate_value;

    public WideWithIINCInstruction(byte format, byte iinc, byte indexByte1, byte indexByte2, byte constByte1, byte constByte2) {
        this.format = format;
        this.wide_opcode = iinc;
        this.local_variable = new byte[] { indexByte1, indexByte2 };
        this.immediate_value =  new byte[] {constByte1, constByte2};
    }

}
