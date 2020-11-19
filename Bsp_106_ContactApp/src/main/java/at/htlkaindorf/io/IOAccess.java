package at.htlkaindorf.io;

import at.htlkaindorf.pojos.Company;
import at.htlkaindorf.pojos.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

public class IOAccess {
    
    @Data
    public final static class ReadResult {
        private final List<Contact> contacts;
        private final List<Company> companies;
    }
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static ReadResult readJson(String path) throws IOException {
        
        List<Contact> contacts = Arrays.asList(mapper.readValue(new File(path), Contact[].class));
        
        List companies = contacts.stream().map(c -> c.getCompany()).collect(Collectors.toList());
        
        return new ReadResult(contacts, companies);
    }
    
}
