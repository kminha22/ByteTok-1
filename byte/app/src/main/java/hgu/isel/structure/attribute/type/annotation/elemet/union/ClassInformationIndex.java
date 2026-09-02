package hgu.isel.structure.attribute.type.annotation.elemet.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ClassInformationIndex extends BaseBytecodeStructure implements ElementUnion {
    private byte[] classInformationIndex; // u2

    public byte[] getClassInformationIndex() {
        return classInformationIndex;
    }

    public void setClassInformationIndex(byte[] classInformationIndex) {
        this.classInformationIndex = classInformationIndex;
    }

    public ClassInformationIndex(byte[] classInformationIndex) {
        this.classInformationIndex = classInformationIndex;
    }

}