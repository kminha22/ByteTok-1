package hgu.isel.structure.field;

import hgu.isel.structure.attribute.AttributeInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class FieldInformation {
    private byte[] accessFlags; // u2
    private byte[] nameIndex; // u2
    private byte[] descriptorIndex; // u2
    private byte[] attributesCount; // u2
    private AttributeInformation[] attributes; // attributesCount

    public byte[] getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(byte[] accessFlags) {
        this.accessFlags = accessFlags;
    }

    public byte[] getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(byte[] nameIndex) {
        this.nameIndex = nameIndex;
    }

    public byte[] getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(byte[] descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public byte[] getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(byte[] attributesCount) {
        this.attributesCount = attributesCount;
    }

    public AttributeInformation[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInformation[] attributes) {
        this.attributes = attributes;
    }

    public FieldInformation(byte[] accessFlags, byte[] nameIndex, byte[] descriptorIndex, byte[] attributesCount, AttributeInformation[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<Start Entry>");
        stringBuilder.append("<Start>--- Type:").append(getClass().getSimpleName()).append("<End>\n");

        stringBuilder.append("<Start>Access Flags:");
        for(byte b : accessFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n");

        stringBuilder.append("<Start>Name Index:");
        for(byte b : nameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n");

        stringBuilder.append("<Start>Descriptor Index:");
        for(byte b : descriptorIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n");

        stringBuilder.append("<Start>Attributes Count:");
        for(byte b : attributesCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        stringBuilder.append("<End>\n");

        stringBuilder.append("<Start>Attribute Information:[");
        for(AttributeInformation a : attributes) {
            stringBuilder.append(a.toString());
        }
        stringBuilder.append("]<End>\n");
        stringBuilder.append("<End Entry>\n");

        return stringBuilder.toString();
    }

    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        output.add("[Field Access Flag]");
        for(byte b : accessFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        output.add("[Field Name Index]");
        for(byte b : nameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        output.add("[Field Descriptor Index]");
        for(byte b : descriptorIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        output.add("[Field Attribute Count]");
        for(byte b : attributesCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(AttributeInformation a : attributes) {
            for(String s : a.tokenize()) {
                output.add("[Field Attribute]");
                stringBuilder.append(s);

                output.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }

        }

        return output;
    }
}
