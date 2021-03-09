package at.htlkaindorf.rss.xml;

import at.htlkaindorf.rss.beans.RSS;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLAccess {
    
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    
    private static XMLAccess instance;
    
    public XMLAccess() throws JAXBException {
        context = JAXBContext.newInstance(RSS.class);
        unmarshaller = context.createUnmarshaller();        
    }

    public static XMLAccess getInstance() throws JAXBException {
        if(instance == null)
            instance = new XMLAccess();
        
        return instance;
    }
    
    public RSS fetchFeed(URL url) throws JAXBException {
        return (RSS) unmarshaller.unmarshal(url);
    }
        
}
