package hgu.isel.structure.attribute.type.code.set.jump;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class JumpOffset extends BaseBytecodeStructure {
    private byte[] info;

    public JumpOffset(byte[] info) {
        this.info = info;
    }

}
