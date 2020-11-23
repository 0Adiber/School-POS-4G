package at.htlkaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Comparable<Company>{
    private String name;
    @EqualsAndHashCode.Exclude
    private String stockmarket;    

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }
}
