package hgu.isel.structure.attribute.type.path;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Path extends BaseBytecodeStructure {
    private byte typePathKind;
    private byte typeArgumentIndex;

    public byte getTypePathKind() {
        return typePathKind;
    }

    public void setTypePathKind(byte typePathKind) {
        this.typePathKind = typePathKind;
    }

    public byte getTypeArgumentIndex() {
        return typeArgumentIndex;
    }

    public void setTypeArgumentIndex(byte typeArgumentIndex) {
        this.typeArgumentIndex = typeArgumentIndex;
    }

    public Path(byte typePathKind, byte typeArgumentIndex) {
        this.typePathKind = typePathKind;
        this.typeArgumentIndex = typeArgumentIndex;
    }
    
}