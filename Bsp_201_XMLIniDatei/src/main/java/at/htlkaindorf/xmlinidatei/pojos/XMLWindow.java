package at.htlkaindorf.xmlinidatei.pojos;

import at.htlkaindorf.xmlinidatei.beans.WindowType;
import at.htlkaindorf.xmlinidatei.bl.XMLHelper;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XMLWindow {
    
    private String name;
    private int xpos,ypos,width,height;
    private WindowType type;
    
    public XMLWindow(Element elem) {
        this(elem.getAttribute("name"), XMLHelper.toInt(XMLHelper.getText(elem, "xpos")), XMLHelper.toInt(XMLHelper.getText(elem, "ypos")), XMLHelper.toInt(XMLHelper.getText(elem, "width")), XMLHelper.toInt(XMLHelper.getText(elem, "height")), XMLHelper.toType(elem.getAttribute("type")));
    }
    
    public void setWindow(Window window) {
        if(type == WindowType.Dialog)
            ((JDialog)window).setTitle(name);
        else 
            ((JFrame)window).setTitle(name);
        
        window.setBounds(xpos,ypos,width,height);
    }
    
    public void update(Window win) {
        xpos = win.getX();
        ypos = win.getY();
        width = win.getWidth();
        height = win.getHeight();
    }
    
    public Element exportXML(Document doc) {
        Element elem = doc.createElement("window");
        elem.setAttribute("name", name);
        elem.setAttribute("type", type.toString());
        elem.appendChild(XMLHelper.element(doc, "xpos", ""+xpos));
        elem.appendChild(XMLHelper.element(doc, "ypos", ""+ypos));
        elem.appendChild(XMLHelper.element(doc, "width", ""+width));
        elem.appendChild(XMLHelper.element(doc, "height", ""+height));
        return elem;
    }
    
    public void updateElement(Element elem) {        
        XMLHelper.updateChild(elem, "xpos", ""+xpos);
        XMLHelper.updateChild(elem, "ypos", ""+ypos);
        XMLHelper.updateChild(elem, "width", ""+width);
        XMLHelper.updateChild(elem, "height", ""+height);
    }
            
}
