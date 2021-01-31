package at.htlkaindorf.xmlinidatei.bl;

import at.htlkaindorf.xmlinidatei.beans.WindowType;
import at.htlkaindorf.xmlinidatei.pojos.XMLWindow;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLIniApp {
    
    public final static String PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "at", "htlkaindorf", "xmlinidatei", "res").toString();
    
    private static XMLIniApp instance;
    private Document doc;
    private File xmlFile;
    
    private List<XMLWindow> windows = new ArrayList<>();
    private static final int[] DEFAULT_POS = {0,0};
    private static final int[] DEFAULT_SIZE = {300,150};
    
    private XMLIniApp() throws ParserConfigurationException {
        loadIniFile(Paths.get(PATH, "windows.xml").toFile());
    }
    
    public static XMLIniApp getInstance() throws ParserConfigurationException {
        if(instance == null)
            instance = new XMLIniApp();
        
        return instance;
    }
    
    private void loadIniFile(File file) throws ParserConfigurationException {
        xmlFile = file;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        try {
            doc = builder.parse(file);
            NodeList nodes = doc.getDocumentElement().getElementsByTagName("window");
            for(int i = 0; i < nodes.getLength(); i++) {
                XMLWindow window = new XMLWindow((Element)nodes.item(i));
                windows.add(window);
            }
        } catch (IOException | SAXException ex) {
            doc = builder.newDocument();
            Element root = doc.createElement("windows");
            doc.appendChild(root);
        }
    }
    
    public XMLWindow initWindow(String name, WindowType type) {
        XMLWindow window = getWindow(name, type);
        if(window == null) {
            window = new XMLWindow(name, DEFAULT_POS[0], DEFAULT_POS[1], DEFAULT_SIZE[0], DEFAULT_SIZE[1], type);
            windows.add(window);
            updateWindow(window);
        }
        
        return window;
    }
    
    public void updateWindow(XMLWindow window) {
        Element elem = getWindowElement(window.getName(), window.getType());
        if(elem == null)
            doc.getDocumentElement().appendChild(window.exportXML(doc));
        else
            window.updateElement(elem);
    }
    
    public void saveIniFile() throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 2);
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        Result result = new StreamResult(xmlFile);
        transformer.transform(new DOMSource(doc), result);
    }
    
    private XMLWindow getWindow(String name, WindowType type) {
        return windows.stream()
                .filter(w -> w.getName().equals(name) && w.getType().equals(type))
                .findFirst()
                .orElse(null);
    }
    
    private Element getWindowElement(String name, WindowType type) {
        NodeList nodes = doc.getDocumentElement().getElementsByTagName("window");
        
        for(int i = 0; i<nodes.getLength(); i++) {
            Element elem = (Element)nodes.item(i);
            if(elem.getAttribute("name").equals(name) && XMLHelper.toType(elem.getAttribute("type")).equals(type)) return elem;
        }
        
        return null;
    }
    
}
