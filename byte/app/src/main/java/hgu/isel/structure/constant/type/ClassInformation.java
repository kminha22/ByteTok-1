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
public class ClassInformation implements ConstantPoolInformation {
    private byte tag; // u1
    private byte[] nameIndex; // u2

    public byte getTag() {
        return tag;
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public ClassInformation(byte tag, byte[] nameIndex) {
        this.tag = tag;
        this.nameIndex = nameIndex;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n    - ClassInformation: ");
        stringBuilder.append(String.format("%02X", tag));

        for(byte b : nameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }


    @Override
    public List<String> tokenize(int index) {
        List<String> output = new ArrayList<>();

        // output.add(String.valueOf(index));

        StringBuilder stringBuilder = new StringBuilder();
        // output.add("[Constant Pool Class Information]");
        stringBuilder.append(String.format("%02X", tag));

        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Constant Pool Information Name Index]");
        // for(byte b : nameIndex) {
        //     stringBuilder.append(String.format("%02X", b));
        // }
        // output.add(stringBuilder.toString());


        return output;
    }
}
