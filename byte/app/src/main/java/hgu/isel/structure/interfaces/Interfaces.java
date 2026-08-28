package hgu.isel.structure.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Interfaces {
    private byte[] bytes; // u2

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Interfaces(byte[] bytes) {
        this.bytes = bytes;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<Start Entry>");
        stringBuilder.append("<Start>--- Type:").append(getClass().getSimpleName()).append("<End>\n");

        stringBuilder.append("<Start>Interface:");
        for(byte b : bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n");
        stringBuilder.append("<End Entry>\n");

        return stringBuilder.toString();
    }

    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        output.add("[Interface]");
        for(byte b : bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        return output;
    }
}
