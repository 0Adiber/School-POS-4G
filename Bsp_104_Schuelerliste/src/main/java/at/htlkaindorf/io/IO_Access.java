package at.htlkaindorf.io;

import at.htlkaindorf.beans.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IO_Access {
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Student> loadData(String path) throws FileNotFoundException, IOException, IndexOutOfBoundsException{
        List<Student> items = new ArrayList<>();
        InputStreamReader ir = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(ir);
        String line = "";
        
        line = br.readLine();
        while((line = br.readLine()) != null){
            String[] parts = line.split(";");
            String fname = parts[2];
            String lname = parts[1];
            String klasse = parts[0];
            String ges = parts[3];
            LocalDate geb = LocalDate.from(DTF.parse(parts[4]));
            items.add(new Student(fname, lname, klasse, ges, geb));
        }
        
        return items;
    }
    
}
