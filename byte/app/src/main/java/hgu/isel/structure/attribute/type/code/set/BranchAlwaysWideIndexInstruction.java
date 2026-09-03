package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;



/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class BranchAlwaysWideIndexInstruction extends AbstractInstruction {
    private byte format;
    private byte[] jump_offset;

    public BranchAlwaysWideIndexInstruction(byte format, byte branchType1, byte branchType2, byte branchType3, byte branchType4) {
        this.format = format;
        this.jump_offset = new byte[] { branchType1, branchType2,  branchType3, branchType4 };
    }
}