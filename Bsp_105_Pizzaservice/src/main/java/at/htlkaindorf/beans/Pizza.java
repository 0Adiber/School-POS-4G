package at.htlkaindorf.beans;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.text.NumberFormatter;

public class Pizza {

    private String name, description, link;
    private double price;

    public Pizza(String name, String description, String link, double price) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.price = price;
    }
    
    public Pizza(String line) {
        String[] parts = line.split(";");
        this.name = parts[0];
        this.description = parts[1];
        this.link = parts[3];
        this.price = Double.parseDouble(parts[2]);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }
    
    public String toHTML(int amount) {
        String res = "";
        
        res = "<li>"
                  + "<div class=\"image\"><img src=\"" + link + "\" /></div>"
                  + "<div class=\"content\"><h2 class=\"name\">" + name + "</h2>"
                  + "<p class=\"desc\">" + description + "</p></div>"
                  + "<h3 class=\"price\">" + NumberFormat.getCurrencyInstance(Locale.GERMANY).format(price) + "</h3>"
                  + "<div class=\"amount-choose\">"
                  + "<button type=\"button\" class=\"bt_decrease\" onclick=\"amount('" + name + "', -1)\">-</button>"
                  + "<input class=\"amount\" id=\"" + name+"_amount" + "\" name=\"" + name+"_amount" + "\" value='" + amount + "' type='number' readonly />"
                  + "<button type=\"button\" class=\"bt_decrease\" onclick=\"amount('" + name + "', 1)\">+</button>"
                  + "</div>"
                  + "</li>";
        
        return res;
    }
    
}
