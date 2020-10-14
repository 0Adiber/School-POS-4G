package at.htlkaindorf.bl;

public class LanguageSelect {
    
    private static String current = "de";

    public static String getCurrent() {
        return current;
    }

    public static void setCurrent(String current) {
        LanguageSelect.current = current;
    }


}
