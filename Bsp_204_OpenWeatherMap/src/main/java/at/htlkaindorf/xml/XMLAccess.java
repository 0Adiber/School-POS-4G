package at.htlkaindorf.xml;

import at.htlkaindorf.beans.Current;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLAccess {
    
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    
    private static XMLAccess instance;
    
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?mode=xml&units=metric&appid=46c394fcd5d0475d05cfdc3077465991";

    public XMLAccess() throws JAXBException {
        context = JAXBContext.newInstance(Current.class);
        unmarshaller = context.createUnmarshaller();
        
    }
    
    public static XMLAccess getInstance() throws JAXBException {
        if(instance == null)
            instance = new XMLAccess();
        return instance;
    }
    
    public Current getWeather(String city, String lang) throws MalformedURLException, JAXBException {
        URL url = new URL(BASE_URL + "&q=" + city + "&lang=" + lang);
        return (Current) unmarshaller.unmarshal(url);
    }
    
}
