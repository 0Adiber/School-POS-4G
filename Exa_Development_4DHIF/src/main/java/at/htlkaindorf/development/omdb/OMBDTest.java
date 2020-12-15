package at.htlkaindorf.development.omdb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OMBDTest {
    
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=82fd3ad1");
            JsonMapper mapper = new JsonMapper();
            
            JsonNode node = mapper.readTree(url);
            
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
            System.out.println(json);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
