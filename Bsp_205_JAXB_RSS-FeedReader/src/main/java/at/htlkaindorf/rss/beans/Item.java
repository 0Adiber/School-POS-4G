package at.htlkaindorf.rss.beans;

import at.htlkaindorf.rss.xml.DateAdapter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @XmlElement
    private String title, link, description;
    @XmlElement
    private Enclosure enclosure;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private ZonedDateTime pubDate;
    @XmlElement
    private String guid;
    
    @XmlTransient
    private boolean read = false;
    
    public String formattedDate() {
        if(pubDate == null)
            return "";
        return pubDate.format(DTF);
    }
}
