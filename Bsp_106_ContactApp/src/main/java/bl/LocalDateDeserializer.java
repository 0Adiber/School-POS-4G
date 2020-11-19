
package bl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.json.stream.JsonParser;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(com.fasterxml.jackson.core.JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return LocalDate.parse(jp.readValueAs(String.class), DTF);
    }
}
