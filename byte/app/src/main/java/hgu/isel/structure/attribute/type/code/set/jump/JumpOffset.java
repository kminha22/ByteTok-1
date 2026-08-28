package hgu.isel.structure.attribute.type.code.set.jump;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class JumpOffset {
    private byte[] info;

    public JumpOffset(byte[] info) {
        this.info = info;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<Start Entry><Start>offset:");
        for(byte b : info) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End><End Entry>\n");

        return stringBuilder.toString();
    }


    public List<String> tokenize() {
        List<String> output = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        output.add("[Jump Offset Information]");

        for(byte b : info) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());


        return output;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("offset", toString());
        return json;
    }
}
