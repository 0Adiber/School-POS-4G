package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Comparable<Company>{
    private String name;
    private String stockmarket;
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public int compareTo(Company o) {
        int res = this.name.compareTo(o.name);
        if(res == 0)
            return this.stockmarket.compareTo(o.stockmarket);
        return res;
    }
}
