package at.htlkaindorf.rss.beans;

import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class FeedSite {
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    private int id;
    private String title;
    private URL url;
    
    public FeedSite(String title, URL url) {
        id = counter.get();
        this.title = title;
        this.url = url;
    }
    
}
