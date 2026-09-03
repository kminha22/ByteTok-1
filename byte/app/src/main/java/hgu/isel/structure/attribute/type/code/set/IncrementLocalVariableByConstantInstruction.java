package hgu.isel.structure.attribute.type.code.set;

import hgu.isel.structure.attribute.type.code.AbstractInstruction;



/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class IncrementLocalVariableByConstantInstruction extends AbstractInstruction {
    private byte format;
    private byte local_variable_index;
    private byte immediate_value;

    public IncrementLocalVariableByConstantInstruction(byte format, byte index, byte constValue) {
        this.format = format;
        this.local_variable_index = index;
        this.immediate_value = constValue;
    }
}