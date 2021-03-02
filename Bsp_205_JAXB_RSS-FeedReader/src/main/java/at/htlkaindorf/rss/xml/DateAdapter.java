package at.htlkaindorf.rss.xml;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("ddd, dd MMM yyyy HH:mm:ss z");
    
    @Override
    public LocalDate unmarshal(String arg0) throws Exception {
        return LocalDate.parse(arg0, DTF);
    }

    @Override
    public String marshal(LocalDate arg0) throws Exception {
        return arg0.format(DTF);
    }
        
}
