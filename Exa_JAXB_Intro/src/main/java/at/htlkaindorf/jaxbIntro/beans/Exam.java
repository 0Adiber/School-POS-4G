package at.htlkaindorf.jaxbIntro.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {

    private short mark;
    private String name;
    
}
