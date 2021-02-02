package at.htlkaindorf.jaxbIntro.test;

import at.htlkaindorf.jaxbIntro.beans.Exam;
import at.htlkaindorf.jaxbIntro.beans.Student;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXB_Tester {
    
    public static void main(String[] args) {
        Student student = new Student(2, "Adi", "Ber");
        student.addExam(new Exam((short)1, "POS"));
        student.addExam(new Exam((short)2, "English"));
        try {
            //Variante 1
            /*JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(student, System.out);*/
            
            //Variante 2
            JAXB.marshal(student, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
