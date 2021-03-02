package at.htlkaindorf.rss.beans;

import at.htlkaindorf.rss.xml.DateAdapter;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    @XmlElement
    private String title, link, description;
    @XmlElement
    private Enclosure enclosure;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate pubDate;
}
