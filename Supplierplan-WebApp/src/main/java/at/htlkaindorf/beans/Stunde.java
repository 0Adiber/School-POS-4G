package at.htlkaindorf.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stunde {

    private String fach;
    private List<String> lehrer;
    private boolean supplierung;

    public Stunde(String line) {
        String[] parts = line.split("\\[,;]\\s*");
        fach = parts[0];
        lehrer = Arrays.asList(parts).stream().skip(1).collect(Collectors.toList());
    }
    
    public Stunde(String fach, List<String> lehrer, boolean supplierung) {
        this.fach = fach;
        this.lehrer = lehrer;
        this.supplierung = supplierung;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public List<String> getLehrer() {
        return lehrer;
    }

    public void setLehrer(List<String> lehrer) {
        this.lehrer = lehrer;
    }

    public boolean isSupplierung() {
        return supplierung;
    }

    public void setSupplierung(boolean supplierung) {
        this.supplierung = supplierung;
    }
    
    
    
}
