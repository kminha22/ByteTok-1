package hgu.isel.structure;

import com.google.gson.JsonElement;
import java.util.List;

public interface JsonSerializable {
    JsonElement toJson();
    List<String> tokenize();
}