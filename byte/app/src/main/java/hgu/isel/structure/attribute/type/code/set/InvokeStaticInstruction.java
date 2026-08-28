package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class InvokeStaticInstruction extends AbstractInstruction {
    private byte format;
    private byte constant_pool_index_1;
    private byte constant_pool_index_2;

    public InvokeStaticInstruction(byte format, byte indexByte1, byte indexByte2) {
        this.format = format;
        this.constant_pool_index_1 = indexByte1;
        this.constant_pool_index_2 = indexByte2;
    }
}