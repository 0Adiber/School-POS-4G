package at.htlkaindorf.pojos;

import at.htlkaindorf.beans.Gender;
import at.htlkaindorf.json.CompanyDeserializer;
import at.htlkaindorf.json.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private int id;
    private String firstname, lastname;
    private String[] email;
    private Gender gender;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    @JsonDeserialize(using = CompanyDeserializer.class)
    private Company company;
}
