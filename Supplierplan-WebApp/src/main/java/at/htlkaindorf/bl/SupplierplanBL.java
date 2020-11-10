package at.htlkaindorf.bl;

import at.htlkaindorf.beans.DAYS;
import at.htlkaindorf.beans.Stunde;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SupplierplanBL {

    //Key = DAYS+[1,2,..]
    private Map<String, Stunde> lessons = new HashMap<>();

    private String klasse = "";
    
    public SupplierplanBL(String path) throws IOException {
        readMap(path);
    }

    public Map<String, Stunde> getLessons() {
        return lessons;
    }
    
    public boolean setLesson(DAYS day, int lesson, String subject, List<String> teachers) {
        Stunde s = lessons.get(getKey(day, lesson));
        if(s == null)
            return false;
        
        s.setFach(subject);
        s.setLehrer(teachers);
        s.setSupplierung(true);
        
        return true;
    }
    
    public void readMap(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Paths.get(path).toFile()));
			String line = br.readLine();
			klasse = line;
			br.close();
        List<Stunde> temp = Files.lines(Paths.get(path)).skip(1).map(Stunde::new).collect(Collectors.toList());
        
        int i = -1;
        for(Stunde s : temp) {
            i++;
            if(s.getFach().startsWith("-"))
                continue;
            lessons.put(getKey(DAYS.values()[i%DAYS.values().length], Math.floorDiv(i, DAYS.values().length)+1), s);
        }
    }
    
    public Stunde getLesson(DAYS day, int lesson) {
        return lessons.get(getKey(day, lesson));
    }
    
    private String getKey(DAYS day, int lesson) {
        return day.toString().substring(0, 2) + ":" + lesson;
    }

    public String getKlasse() {
        return klasse;
    }
    
}
