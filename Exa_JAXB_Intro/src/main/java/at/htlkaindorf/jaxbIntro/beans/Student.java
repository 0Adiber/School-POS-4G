package at.htlkaindorf.jaxbIntro.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @NonNull
    @XmlAttribute
    private int catalogNo;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @EqualsAndHashCode.Exclude
    @XmlElement(name="exam")
    @XmlElementWrapper(name="exams")
    private List<Exam> exams = new ArrayList<>();
    
    public void addExam(Exam exam) {
        exams.add(exam);
    }
    
}
