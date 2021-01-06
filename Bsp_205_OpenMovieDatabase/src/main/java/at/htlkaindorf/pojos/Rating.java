package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rating {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
    
    
}
