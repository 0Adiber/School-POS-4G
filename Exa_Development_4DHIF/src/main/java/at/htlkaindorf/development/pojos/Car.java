package at.htlkaindorf.development.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data   //shortcut für @Getter, @Setter, @ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String brand;
    private String type;
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
    private double weight;
        
    public static void main(String[] args) {
        Car c1 = new Car("vw", "polo", 1000);
        Car c2 = new Car();
        Car[] cars = {c1, c2};
        System.out.println(c1);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            System.out.println("Json - write:");
            String json = mapper.writerWithDefaultPrettyPrinter()
                      .writeValueAsString(cars);
            System.out.println(json);
            
            System.out.println("Json - read:");
            String carObj = "{\"brand\":\"vw\",\"type\":\"polo\",\"weight\":1000.0}";
            Car carFromJsonString = mapper.readValue(carObj, Car.class);
            System.out.println(carFromJsonString);
            
            String carArray = "[{\"brand\":\"vw\",\"type\":\"polo\",\"weight\":1000.0},{\"brand\":null,\"type\":null,\"weight\":0.0}]";
            Car[] carsFromJsonString = mapper.readValue(carArray, Car[].class);
            System.out.println(Arrays.toString(carsFromJsonString));
            
        } catch (JsonProcessingException ex) {
            System.out.println(ex.toString());
        }
        
    }
}
