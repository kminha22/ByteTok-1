package hgu.isel.structure.attribute.type.module.export;

import hgu.isel.structure.BaseBytecodeStructure;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ExportIndex extends BaseBytecodeStructure {
    private byte[] exportsToIndex; // u2

    public byte[] getExportsToIndex() {
        return exportsToIndex;
    }

    public void setExportsToIndex(byte[] exportsToIndex) {
        this.exportsToIndex = exportsToIndex;
    }

    public ExportIndex(byte[] exportsToIndex) {
        this.exportsToIndex = exportsToIndex;
    }
    
}