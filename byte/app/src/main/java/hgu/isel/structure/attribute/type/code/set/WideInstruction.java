package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class WideInstruction extends AbstractInstruction {
    private byte format;
    private byte wide_opcode;
    private byte[] local_variable;

    public WideInstruction(byte format, byte opCode, byte indexByte1, byte indexByte2) {
        this.format = format;
        this.wide_opcode = opCode;
        this.local_variable = new byte[] { indexByte1, indexByte2 };
    }

}
