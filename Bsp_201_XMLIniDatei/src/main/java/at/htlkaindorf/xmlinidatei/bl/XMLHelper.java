package at.htlkaindorf.xmlinidatei.bl;

import at.htlkaindorf.xmlinidatei.beans.WindowType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLHelper {
    
    public static int toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e) {
            return 0;
        } 
    }
    
    public static WindowType toType(String type) {
        try {
            return WindowType.valueOf(type);
        } catch(IllegalArgumentException | NullPointerException e) {
            return WindowType.Frame;
        }
    }
    
    public static String getText(Element elem, String tag) {
        return elem.getElementsByTagName(tag).item(0).getTextContent();
    }
    
    public static Element element(Document doc, String tag, String content) {
        Element elem = doc.createElement(tag);
        elem.setTextContent(content);
        return elem;
    }
    
    public static void updateChild(Element elem, String tag, String content) {
        Element child = (Element) elem.getElementsByTagName(tag).item(0);
        child.setTextContent(content);
    }
    
}
