package at.htlkaindorf.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {
    //Klasse;Familienname;Vorname;Geschlecht;2001-08-27
    private static int currentId = 0;
    
    private static Map<String, AtomicInteger> ClassCatNr = new HashMap<>();
    
    public static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static DateTimeFormatter DTF1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private int uid, catnr;
    private String firstname, lastname, klasse, geschlecht;
    private LocalDate birth;

    public Student(String line) {
        String[] parts = line.split(";");
        this.firstname = parts[2];
        this.lastname = parts[1];
        this.klasse = parts[0];
        this.geschlecht = parts[3].toUpperCase();
        this.birth = LocalDate.from(DTF1.parse(parts[4]));
        
        this.uid = currentId++;
        ClassCatNr.putIfAbsent(klasse, new AtomicInteger(1));
        catnr = ClassCatNr.get(klasse).getAndIncrement();
    }
    
    public Student(String firstname, String lastname, String klasse, String geschlecht, LocalDate birth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.klasse = klasse;
        this.geschlecht = geschlecht.toUpperCase();
        this.birth = birth;
        
        this.uid = currentId++;
        ClassCatNr.putIfAbsent(klasse, new AtomicInteger(1));
        catnr = ClassCatNr.get(klasse).getAndIncrement();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public LocalDate getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return String.format("<option value=\"%d\">%s %s</option>", uid, lastname, firstname);
    }
    
    public int getUid() {
        return uid;
    }

    public int getCatnr() {
        return catnr;
    }

    
    
    
}
