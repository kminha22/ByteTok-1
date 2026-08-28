package hgu.isel.structure.attribute.type;

import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.module.Exports;
import hgu.isel.structure.attribute.type.module.Opens;
import hgu.isel.structure.attribute.type.module.Provides;
import hgu.isel.structure.attribute.type.module.Requires;
import hgu.isel.structure.attribute.type.module.uses.UsesIndex;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Module implements AttributeInformation {
    private byte[] attributeNameIndex; // u2
    private byte[] attributeLength; // u4
    private byte[] moduleNameIndex; // u2
    private byte[] moduleFlags; // u2
    private byte[] moduleVersionIndex; // u2
    private byte[] requiresCount; // u2
    private Requires[] requires; // requiresCount
    private byte[] exportsCount; // u2
    private Exports[] exports; // exportsCount
    private byte[] opensCount; // u2
    private Opens[] opens; // opensCount
    private byte[] usesCount; // u2
    private UsesIndex[] usesIndex; // usesCount
    private byte[] providesCount; // u2
    private Provides[] provides; // providesCount

    public byte[] getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(byte[] attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public byte[] getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(byte[] attributeLength) {
        this.attributeLength = attributeLength;
    }

    public byte[] getModuleNameIndex() {
        return moduleNameIndex;
    }

    public void setModuleNameIndex(byte[] moduleNameIndex) {
        this.moduleNameIndex = moduleNameIndex;
    }

    public byte[] getModuleFlags() {
        return moduleFlags;
    }

    public void setModuleFlags(byte[] moduleFlags) {
        this.moduleFlags = moduleFlags;
    }

    public byte[] getModuleVersionIndex() {
        return moduleVersionIndex;
    }

    public void setModuleVersionIndex(byte[] moduleVersionIndex) {
        this.moduleVersionIndex = moduleVersionIndex;
    }

    public byte[] getRequiresCount() {
        return requiresCount;
    }

    public void setRequiresCount(byte[] requiresCount) {
        this.requiresCount = requiresCount;
    }

    public Requires[] getRequires() {
        return requires;
    }

    public void setRequires(Requires[] requires) {
        this.requires = requires;
    }

    public byte[] getExportsCount() {
        return exportsCount;
    }

    public void setExportsCount(byte[] exportsCount) {
        this.exportsCount = exportsCount;
    }

    public Exports[] getExports() {
        return exports;
    }

    public void setExports(Exports[] exports) {
        this.exports = exports;
    }

    public byte[] getOpensCount() {
        return opensCount;
    }

    public void setOpensCount(byte[] opensCount) {
        this.opensCount = opensCount;
    }

    public Opens[] getOpens() {
        return opens;
    }

    public void setOpens(Opens[] opens) {
        this.opens = opens;
    }

    public byte[] getUsesCount() {
        return usesCount;
    }

    public void setUsesCount(byte[] usesCount) {
        this.usesCount = usesCount;
    }

    public UsesIndex[] getUsesIndex() {
        return usesIndex;
    }

    public void setUsesIndex(UsesIndex[] usesIndex) {
        this.usesIndex = usesIndex;
    }

    public byte[] getProvidesCount() {
        return providesCount;
    }

    public void setProvidesCount(byte[] providesCount) {
        this.providesCount = providesCount;
    }

    public Provides[] getProvides() {
        return provides;
    }

    public void setProvides(Provides[] provides) {
        this.provides = provides;
    }

    public Module(byte[] attributeNameIndex, byte[] attributeLength, byte[] moduleNameIndex, byte[] moduleFlags, byte[] moduleVersionIndex, byte[] requiresCount, Requires[] requires, byte[] exportsCount, Exports[] exports, byte[] opensCount, Opens[] opens, byte[] usesCount, UsesIndex[] usesIndex, byte[] providesCount, Provides[] provides) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.moduleNameIndex = moduleNameIndex;
        this.moduleFlags = moduleFlags;
        this.moduleVersionIndex = moduleVersionIndex;
        this.requiresCount = requiresCount;
        this.requires = requires;
        this.exportsCount = exportsCount;
        this.exports = exports;
        this.opensCount = opensCount;
        this.opens = opens;
        this.usesCount = usesCount;
        this.usesIndex = usesIndex;
        this.providesCount = providesCount;
        this.provides = provides;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<Start Entry>");
        sb.append("<Start>--- Type:").append(getClass().getSimpleName()).append("<End>\n");

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                if (value == null) continue;

                sb.append("<Start>")
                .append(field.getName())
                .append(":");

                if (value instanceof Byte) {
                    sb.append(String.format("%02X", value));
                } else if (value instanceof byte[]) {
                    sb.append(bytesToHex((byte[]) value));
                } else if (value.getClass().isArray()) {
                    // 배열 처리
                    Object[] arr = (Object[]) value;
                    sb.append("[");
                    for (int i = 0; i < arr.length; i++) {
                        Object elem = arr[i];
                        if (elem != null) {
                            sb.append(elem.toString()); // 각 객체의 toString() 호출
                        } else {
                            sb.append("null");
                        }
                    }
                    sb.append("]");
                } else {
                    sb.append(value.toString());
                }

                sb.append("<End>");
            } catch (IllegalAccessException e) {
                // 무시
            }
        }

        sb.append("<End Entry>");
        return sb.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }

    @Override
    public List<String> tokenize() {
        List<String> output = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        // output.add("[Module Attribute Name Index]");
        for(byte b : attributeNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Module Attribute Length]");
        for(byte b : attributeLength) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Module Attribute Module Name Index]");
        for(byte b : moduleNameIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Module Attribute Flag]");
        for(byte b : moduleFlags) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Module Attribute Version Index]");
        for(byte b : moduleVersionIndex) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        // output.add("[Module Attribute Requires Count]");
        for(byte b : requiresCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(Requires l : requires) {
            output.addAll(l.tokenize());
        }

        // output.add("[Module Attribute Exports Count]");
        for(byte b : exportsCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(Exports l : exports) {
            output.addAll(l.tokenize());
        }


        // output.add("[Module Attribute Opens Count]");
        for(byte b : opensCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(Opens l : opens) {
            output.addAll(l.tokenize());
        }


        // output.add("[Module Attribute Uses Count]");
        for(byte b : usesCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(UsesIndex l : usesIndex) {
            output.addAll(l.tokenize());
        }
        // output.add("[Module Attribute Provides Count]");
        for(byte b : providesCount) {
            stringBuilder.append(String.format("%02X", b));
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(Provides l : provides) {
            output.addAll(l.tokenize());
        }

        return output;
    }
}
