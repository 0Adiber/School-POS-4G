package at.htlkaindorf.io;

import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import bl.CompanyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.Data;

public class IOAccess {
    
    @Data
    public final static class ReadResult {
        private final List<Contact> contacts;
        private final Set<Company> companies;
    }
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static ReadResult readJson(String path) throws IOException {
        
        List<Contact> contacts = Arrays.asList(mapper.readValue(new File(path), Contact[].class));
        
        Set<Company> companies = CompanyDeserializer.companies;
        
        return new ReadResult(contacts, companies);
    }
    
}
