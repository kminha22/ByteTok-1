package hgu.isel.structure.attribute.type.annotation.elemet.union;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.annotation.ElementValue;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ArrayValue extends BaseBytecodeStructure implements ElementUnion {
    private byte[] numValues; // u2
    private ElementValue[] values; // numValues;

    public byte[] getNumValues() {
        return numValues;
    }

    public void setNumValues(byte[] numValues) {
        this.numValues = numValues;
    }

    public ElementValue[] getValues() {
        return values;
    }

    public void setValues(ElementValue[] values) {
        this.values = values;
    }

    public ArrayValue(byte[] numValues, ElementValue[] values) {
        this.numValues = numValues;
        this.values = values;
    }

}