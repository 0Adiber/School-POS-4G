package at.htlkaindorf.development.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate>{

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
    
    public LocalDateSerializer() {
        super(LocalDate.class);
    }
    
    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider sp) throws IOException {
        gen.writeString(value.format(DTF));
    }

}
