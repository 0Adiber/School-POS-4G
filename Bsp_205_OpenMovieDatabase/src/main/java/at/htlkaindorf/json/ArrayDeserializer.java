package at.htlkaindorf.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.HashSet;

public class ArrayDeserializer extends StdDeserializer<String[]>{
    
    protected ArrayDeserializer() {
        super(String[].class);
    }
    
    @Override
    public String[] deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        final String all = jp.readValueAs(String.class);
        
        return all.split(", ");
    }
    
}
