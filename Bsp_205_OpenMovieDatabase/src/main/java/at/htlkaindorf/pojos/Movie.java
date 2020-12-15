package at.htlkaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private String Title,Runtime,Year,Rated,Released,Language,Plot,Country,Awards,Poster,imdbID;
    private String[] Director,Genre,Writer,Actors,Production;
    private Rating[] Ratings;

}
