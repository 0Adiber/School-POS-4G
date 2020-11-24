package at.htlkaindorf.development.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate>{

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
    
    public LocalDateDeserializer() {
        super(LocalDate.class);
    }
    
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return LocalDate.parse(dc.readValue(jp, String.class), DTF);
    }

}
