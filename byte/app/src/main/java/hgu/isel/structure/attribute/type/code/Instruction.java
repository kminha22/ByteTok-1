package hgu.isel.structure.attribute.type.code;

import java.util.List;


public interface Instruction {
    String getTagName();
    List<String> tokenize(boolean includeTag, String delimiter) ;
}
