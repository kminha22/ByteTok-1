package hgu.isel.structure.attribute.type.packages;

import hgu.isel.structure.BaseBytecodeStructure;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class PackageIndex extends BaseBytecodeStructure {
    private byte[] packageIndex; // u2

    public byte[] getPackageIndex() {
        return packageIndex;
    }

    public void setPackageIndex(byte[] packageIndex) {
        this.packageIndex = packageIndex;
    }

    public PackageIndex(byte[] packageIndex) {
        this.packageIndex = packageIndex;
    }
    
}