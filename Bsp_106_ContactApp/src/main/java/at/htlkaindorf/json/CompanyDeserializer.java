package at.htlkaindorf.json;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import at.htlkaindorf.pojos.Company;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CompanyDeserializer extends StdDeserializer<Company>{

    public static final Set<Company> companies = new HashSet<>();
    
    protected CompanyDeserializer() {
        super(Company.class);
    }
    
    @Override
    public Company deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        final Company company = jp.readValueAs(Company.class);
        
        companies.add(company);
        return companies.stream().filter(c -> c.equals(company)).findFirst().get();
    }
    
}
