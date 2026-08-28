package hgu.isel.structure.attribute.type.code.set.match;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class MatchOffsetPair {
    private byte[] match;
    private byte[] offset;

    public MatchOffsetPair(byte[] match, byte[] offset) {
        this.match = match;
        this.offset = offset;
    }

    public byte[] getMatch() {
        return match;
    }

    public void setMatch(byte[] match) {
        this.match = match;
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<Start Entry>");
        stringBuilder.append("<Start>match:");
        
        for(byte b : match) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End><Start>offset:");

        for(byte b : offset) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n<End Entry>\n");

        return stringBuilder.toString();
    }

    public List<String> tokenize() {
        List<String> output = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        output.add("[Match]");
        for(byte b : match) {
            stringBuilder.append(String.format("%02X", b));
        }

        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        output.add("[Offset]");
        for(byte b : offset) {
            stringBuilder.append(String.format("%02X", b));
        }

        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        return output;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("match", bytesToHex(match));
        json.addProperty("offset", bytesToHex(offset));
        return json;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
