package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;



/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class FetchFieldFromObjectInstruction extends AbstractInstruction {
    private byte format;
    private byte[] constant_pool_index;

    public FetchFieldFromObjectInstruction(byte format, byte indexByte1, byte indexByte2) {
        this.format = format;
        this.constant_pool_index = new byte[] { indexByte1, indexByte2 };
    }
}