package at.htlkaindorf.bl;

import java.util.HashMap;
import java.util.Map;

public class LanguageSelect {
    
    private final static Map<String,Map<String,String>> language = new HashMap<>();

    public LanguageSelect() {
    }
    
    static {
        Map<String,String> de = new HashMap<>();
        de.put("back", "Zurück (Bestellung leeren)");
        de.put("backsave", "Zurück (Bestellung beibehalten)");
        de.put("address", "Lieferadresse");
        de.put("order", "Bestellen");
        de.put("pizza", "Pizza");
        de.put("price", "Preis");
        de.put("amount", "Stück");
        de.put("total", "Gesamt");
        de.put("sum", "Summe");
        de.put("erraddr", "Bitte eine Lieferadresse eingeben.");
        de.put("errpizza", "Bitte zumindest eine Pizza auswählen.");
        language.put("de", de);
        
        Map<String,String> en = new HashMap<>();
        en.put("back", "Back (Clear Order)");
        en.put("backsave", "Back (Keep Order)");
        en.put("address", "Delivery address");
        en.put("order", "Order");
        en.put("pizza", "Pizza");
        en.put("price", "Price");
        en.put("amount", "Amount");
        en.put("total", "Total");
        en.put("sum", "Sum");
        en.put("erraddr", "Please insert a delivery address.");
        en.put("errpizza", "Please choose at least one pizza.");
        language.put("en", en);
    }

    public static Map<String,String> getLanguage(String current) {
        return language.get(current);
    }

}
