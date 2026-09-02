package hgu.isel.structure.attribute.type.annotation;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ElementValuePairs extends BaseBytecodeStructure {
    private byte[] elementNameIndex; // u2
    private ElementValue value;

    public byte[] getElementNameIndex() {
        return elementNameIndex;
    }

    public void setElementNameIndex(byte[] elementNameIndex) {
        this.elementNameIndex = elementNameIndex;
    }

    public ElementValue getValue() {
        return value;
    }

    public void setValue(ElementValue value) {
        this.value = value;
    }

    public ElementValuePairs(byte[] elementNameIndex, ElementValue value) {
        this.elementNameIndex = elementNameIndex;
        this.value = value;
    }

}