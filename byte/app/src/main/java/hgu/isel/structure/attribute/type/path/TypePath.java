package hgu.isel.structure.attribute.type.path;

import hgu.isel.structure.BaseBytecodeStructure;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class TypePath extends BaseBytecodeStructure {
    private byte pathLength;
    private Path[] path; // pathLength

    public byte getPathLength() {
        return pathLength;
    }

    public void setPathLength(byte pathLength) {
        this.pathLength = pathLength;
    }

    public Path[] getPath() {
        return path;
    }

    public void setPath(Path[] path) {
        this.path = path;
    }

    public TypePath(byte pathLength, Path[] path) {
        this.pathLength = pathLength;
        this.path = path;
    }

}