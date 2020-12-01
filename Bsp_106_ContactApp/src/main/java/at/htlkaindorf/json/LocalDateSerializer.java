package at.htlkaindorf.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate>{

    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public LocalDateSerializer() {
        super(LocalDate.class);
    }
    
    @Override
    public void serialize(LocalDate t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(DTF.format(t));
    }

}
