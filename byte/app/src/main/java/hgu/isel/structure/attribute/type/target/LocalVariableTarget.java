package hgu.isel.structure.attribute.type.target;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.target.local.LocalVariableTargetTable;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class LocalVariableTarget extends BaseBytecodeStructure implements AttributeInformation {
    private byte[] tableLength; // u2
    private LocalVariableTargetTable[] table;

    public byte[] getTableLength() {
        return tableLength;
    }

    public void setTableLength(byte[] tableLength) {
        this.tableLength = tableLength;
    }

    public LocalVariableTargetTable[] getTable() {
        return table;
    }

    public void setTable(LocalVariableTargetTable[] table) {
        this.table = table;
    }

    public LocalVariableTarget(byte[] tableLength, LocalVariableTargetTable[] table) {
        this.tableLength = tableLength;
        this.table = table;
    }
    
}