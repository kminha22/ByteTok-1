package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class BranchAlwaysInstruction extends AbstractInstruction {
    private byte format;
    private byte[] jump_offset;

    public BranchAlwaysInstruction(byte format, byte branchType1, byte branchType2) {
        this.format = format;
        this.jump_offset = new byte[] { branchType1, branchType2 };
    }
}