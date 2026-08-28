package hgu.isel.structure.attribute.type.code;

import java.util.List;
import java.util.Map;


public interface Instruction {

    String getTagName();
    Map<String, String> getFields() ;
    List<String> tokenize(boolean includeTag, String delimiter) ;
    String toCustomString();

}
