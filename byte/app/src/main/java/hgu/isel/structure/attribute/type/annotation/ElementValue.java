package hgu.isel.structure.attribute.type.annotation;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.type.annotation.elemet.ElementUnion;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class ElementValue extends BaseBytecodeStructure {
    private byte tag;
    private ElementUnion elementUnion;

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public ElementUnion getElementUnion() {
        return elementUnion;
    }

    public void setElementUnion(ElementUnion elementUnion) {
        this.elementUnion = elementUnion;
    }

    public ElementValue(byte tag, ElementUnion elementUnion) {
        this.tag = tag;
        this.elementUnion = elementUnion;
    }

}