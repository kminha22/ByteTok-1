package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class InvokeInterfaceInstruction extends AbstractInstruction {
    private byte format;
    private byte[] constant_pool_index;
    private byte immediate_value;
    private final byte ignore = 0;

    public InvokeInterfaceInstruction(byte format, byte indexByte1, byte indexByte2, byte count) {
        this.format = format;
        this.constant_pool_index = new byte[] { indexByte1, indexByte2 };
        this.immediate_value = count;
    }
}