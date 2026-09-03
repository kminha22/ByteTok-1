package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;



/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class JumpSubroutineInstruction extends AbstractInstruction {
    private byte format;
    private byte[] jump_offset;

    public JumpSubroutineInstruction(byte format, byte branchByte1, byte branchByte2) {
        this.format = format;
        this.jump_offset = new byte[] { branchByte1, branchByte2 };
    }
}