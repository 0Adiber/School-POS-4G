package domintro.xml;

import domintro.beans.Address;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLAccess {
    
    private Document doc;
    
    /**
     * Deserialization of xml-Document from file into Java-DOM-Object
     * @param file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public void loadFromXmlFile(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(file);
    }
    
    public void saveToXmlFile(File file) throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 2);
        
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        Result record = new StreamResult(file);
        transformer.transform(new DOMSource(doc), record);
    }
    
    public List<Address> getAddressList() {
        List<Address> adrList = new ArrayList<>();
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            //System.out.println(node.getNodeType());
            if(node instanceof Element) {
                adrList.add(convertNodeToAddress((Element)node));
            }
        }
        
        return adrList;
    }
    
    /**
     * removes all addresses the dom with the specified country
     * @param country 
     */
    public void removeAddressesByCountry(String country) {        
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if(((Element)n).getElementsByTagName("country").item(0).getTextContent().equalsIgnoreCase(country)) {
                doc.getDocumentElement().removeChild(n);
            }
        }
    }
    
    /**
     * add unique ID attribute
     */
    public void addIdAsAttributeToAddress() {
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            ((Element)n).setAttribute("id", ""+i);
        }
    }
    
    /**
     * creates a new DOM-document that contains only address-nodes of the specified gender
     * @param gender
     * @return 
     */
    public Document createDOMByGender(String gender) throws ParserConfigurationException {
        Document gDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = gDoc.createElement("addresses");
        gDoc.appendChild(root);
        
        NodeList nl = doc.getDocumentElement().getElementsByTagName("address");
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            if(((Element)n).getElementsByTagName("gender").item(0).getTextContent().equalsIgnoreCase(gender))
                root.appendChild(gDoc.adoptNode(n.cloneNode(true)));
        }
        return gDoc;
    }
    
    public Address convertNodeToAddress(Element elem) {
        String name = elem.getElementsByTagName("name").item(0).getTextContent();
        String country = elem.getElementsByTagName("country").item(0).getTextContent();
        String city = elem.getElementsByTagName("city").item(0).getTextContent();
        String email = elem.getElementsByTagName("email").item(0).getTextContent();
        String gender = elem.getElementsByTagName("gender").item(0).getTextContent();
        String timezone = elem.getElementsByTagName("timezone").item(0).getTextContent();
        return new Address(name, country, city, email, gender, timezone);
    }
    
    public void insertAddress(Address address) {
        Element addrElement = doc.createElement("address"); // create new Element
        doc.getDocumentElement().appendChild(addrElement);  // insert new Element into DOM tree
        
        insertChild(addrElement, "name", address.getName());
        insertChild(addrElement, "country", address.getCountry());
        insertChild(addrElement, "city", address.getCity());
        insertChild(addrElement, "email", address.getEmail());
        insertChild(addrElement, "gender", address.getGender());
        insertChild(addrElement, "timezone", address.getTimezone());
    }
    
    public void insertChild(Element parent, String tagname, String content) {
        Element elem = doc.createElement(tagname);
        elem.setTextContent(content);
        parent.appendChild(elem);        
    }
    
    public static void main(String[] args) {
        XMLAccess xmla = new XMLAccess();
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses.xml");
        Path path2 = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "res", "addresses_new.xml");
        try {
            xmla.loadFromXmlFile(path.toFile());
            
            //xmla.saveToXmlFile(path2.toFile());
            
            /*for(Address address : xmla.getAddressList()) {
                System.out.println(address);
            }*/
            
            //xmla.insertAddress(new Address("Joe Biden", "USA", "Washington", "president@gov.us", "Male", "N/A"));
            
            xmla.removeAddressesByCountry("Brazil");
            xmla.addIdAsAttributeToAddress();
            Document test = xmla.createDOMByGender("Male");
            xmla.setDoc(test);
            
            xmla.saveToXmlFile(path2.toFile());
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
}
