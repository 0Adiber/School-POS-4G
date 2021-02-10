package at.htlkaindorf.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Current {
    @XmlElement
    private City city;
    @XmlElement(name="temperature")
    private Temperature temp;
    @XmlElement(name="feels_like")
    private Feeling feeling;
    @XmlElement
    private Humidity humidity;
    @XmlElement
    private Pressure pressure;
    @XmlElement
    private Wind wind;
    @XmlElement
    private Clouds clouds;
    @XmlElement
    private Weather weather;
}
