package at.htlkaindorf.beans;

import at.htlkaindorf.bl.LanguageSelect;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.text.NumberFormatter;

public class Pizza {

    private static AtomicInteger CURID = new AtomicInteger(0);
    
    private String link;
    private double price;
    
    private int id;
    
    private Map<String,String> names = new HashMap<>();
    private Map<String,String> descriptions = new HashMap<>();
    
    public Pizza(String line) {
        this.id = CURID.getAndIncrement();
        String[] parts = line.split(";");
        names.put("de", parts[0]);
        names.put("en", parts[1]);
        descriptions.put("de", parts[2]);
        descriptions.put("en", parts[3]);
        this.link = parts[5];
        this.price = Double.parseDouble(parts[4]);
    }

    public String getName(String lang) {
        return names.get(lang);
    }

    public String getDescription(String lang) {
        return descriptions.get(lang);
    }

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
    
    public String toHTML(int amount, String lang) {
        String res = "";
        
        res = "<li>"
                  + "<div class=\"image\"><img src=\"" + link + "\" /></div>"
                  + "<div class=\"content\"><h2 class=\"name\">" + getName(lang) + "</h2>"
                  + "<p class=\"desc\">" + getDescription(lang) + "</p></div>"
                  + "<h3 class=\"price\">" + NumberFormat.getCurrencyInstance(Locale.GERMANY).format(price) + "</h3>"
                  + "<div class=\"amount-choose\">"
                  + "<button type=\"button\" class=\"bt_decrease\" onclick=\"amount('" + id + "', -1)\">-</button>"
                  + "<input class=\"amount\" id=\"" + id+"_amount" + "\" name=\"" + id+"_amount" + "\" value='" + amount + "' type='number' readonly />"
                  + "<button type=\"button\" class=\"bt_decrease\" onclick=\"amount('" + id + "', 1)\">+</button>"
                  + "</div>"
                  + "</li>";
        
        return res;
    }
    
}
