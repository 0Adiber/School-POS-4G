package at.htlkaindorf.bl;

import java.util.HashMap;
import java.util.Map;

public class LanguageSelect {
    
    private final static Map<String,Map<String,String>> language = new HashMap<>();
    
    static {
        Map<String,String> de = new HashMap<>();
        de.put("back", "Zurück");
        de.put("address", "Lieferadresse");
        de.put("order", "Bestellen");
        de.put("pizza", "Pizza");
        de.put("price", "Preis");
        de.put("amount", "Stück");
        de.put("total", "Gesamt");
        de.put("sum", "Summe");
        language.put("de", de);
        
        Map<String,String> en = new HashMap<>();
        en.put("back", "Back");
        en.put("address", "Delivery address");
        en.put("order", "Order");
        en.put("pizza", "Pizza");
        en.put("price", "Price");
        en.put("amount", "Amount");
        en.put("total", "Total");
        en.put("sum", "Sum");
        language.put("en", en);
    }

    public static Map<String,String> getLanguage(String current) {
        return language.get(current);
    }

}
