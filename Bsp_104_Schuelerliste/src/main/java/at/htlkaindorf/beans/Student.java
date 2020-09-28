package at.htlkaindorf.beans;

import java.time.LocalDate;

public class Student {
    //Klasse;Familienname;Vorname;Geschlecht;2001-08-27
    private static int currentId = 0;
    private int uid;
    private String firstname, lastname, klasse, geschlecht;
    private LocalDate birth;

    public Student(String firstname, String lastname, String klasse, String geschlecht, LocalDate birth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.klasse = klasse;
        this.geschlecht = geschlecht;
        this.birth = birth;
        this.uid = currentId++;
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

    
    
}
