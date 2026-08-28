package hgu.isel.structure.constant.type;

import hgu.isel.structure.constant.ConstantPoolInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class DoubleInformation implements ConstantPoolInformation {
    private byte tag;
    private byte[] highBytes; // u4
    private byte[] lowBytes; // u4

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getHighBytes() {
        return highBytes;
    }

    public void setHighBytes(byte[] highBytes) {
        this.highBytes = highBytes;
    }

    public byte[] getLowBytes() {
        return lowBytes;
    }

    public void setLowBytes(byte[] lowBytes) {
        this.lowBytes = lowBytes;
    }

    public DoubleInformation(byte tag, byte[] highBytes, byte[] lowBytes) {
        this.tag = tag;
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n    - DoubleInformation: ");
        stringBuilder.append(String.format("%02X", tag));

        for(byte b : highBytes) {
            stringBuilder.append(String.format("%02X", b));
        }

        for(byte b : lowBytes) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }

    @Override
    public List<String> tokenize(int index) {
        List<String> output = new ArrayList<>();
        // output.add(String.valueOf(index));

        StringBuilder stringBuilder = new StringBuilder();
        // output.add("[Constant Double]");
        stringBuilder.append(String.format("%02X", tag));
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Constant Double High Byte]");
        // for(byte b : highBytes) {
        //     stringBuilder.append(String.format("%02X", b));
        // }
        // output.add(stringBuilder.toString());
        // stringBuilder.setLength(0);

        // output.add("[Constant Double Low Byte]");
        // for(byte b : lowBytes) {
        //     stringBuilder.append(String.format("%02X", b));
        // }
        // output.add(stringBuilder.toString());


        return output;
    }
}
