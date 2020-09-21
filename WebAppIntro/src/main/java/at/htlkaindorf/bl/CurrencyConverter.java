package at.htlkaindorf.bl;

public class CurrencyConverter {

    private static final float[] CONV = {
        1.62F, 
        1.57F, 
        123.72F, 
        2751.76F, 
        0.00011F
    };
    private static final String[] CONVN = {
        "Australische Dollar", 
        "Kanadische Dollar", 
        "Yen", 
        "Tansania Schilling", 
        "Bitcoin"
    };
    
    public static float convertFromEurToIndex(float value, int targetIndex) {
        if(targetIndex < 0 || targetIndex > 4) {
            throw new IllegalArgumentException("currency index not supported");
        }
        return value*CONV[targetIndex];
    }
    
    public static String getCurrencyNameFromIndex(int targetIndex) {
        return CONVN[targetIndex];
    }
}
