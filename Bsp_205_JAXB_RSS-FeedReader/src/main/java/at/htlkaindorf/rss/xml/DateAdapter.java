package at.htlkaindorf.rss.xml;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, ZonedDateTime> {

    private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    
    @Override
    public ZonedDateTime unmarshal(String arg0) throws Exception {
        return ZonedDateTime.parse(arg0, DTF);
    }

    @Override
    public String marshal(ZonedDateTime arg0) throws Exception {
        return arg0.format(DTF);
    }
        
}
