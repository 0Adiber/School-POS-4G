package at.htlkaindorf.rss.beans;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class FeedSite {
    private static final AtomicInteger counter = new AtomicInteger(0);
    
    private int id;
    private String title;
    private String url;
    
    public FeedSite(String title, String url) {
        id = counter.get();
        this.title = title;
        this.url = url;
    }
    
}
