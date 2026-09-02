package hgu.isel.structure.attribute.type;

import hgu.isel.structure.BaseBytecodeStructure;
import hgu.isel.structure.attribute.AttributeInformation;
import hgu.isel.structure.attribute.type.module.Exports;
import hgu.isel.structure.attribute.type.module.Opens;
import hgu.isel.structure.attribute.type.module.Provides;
import hgu.isel.structure.attribute.type.module.Requires;
import hgu.isel.structure.attribute.type.module.uses.UsesIndex;

/**
 * This class supports the structure of the JVM bytecodes.
 * By overriding the toString(), tokenize() methods, it can analyze / tokenize the bytecodes.
 * <p>
 * All getters and setters in this class are simple property accessors with no side effects.
 */
public class Module extends BaseBytecodeStructure implements AttributeInformation {
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
    
}
