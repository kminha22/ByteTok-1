package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;


/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class StoreIntoReferenceArrayInstruction extends AbstractInstruction { // aastore
    private byte format;

    public StoreIntoReferenceArrayInstruction(byte format) {
        this.format = format;
    }

}