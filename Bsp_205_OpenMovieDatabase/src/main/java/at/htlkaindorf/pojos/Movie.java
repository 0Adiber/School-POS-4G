package at.htlkaindorf.pojos;

import at.htlkaindorf.json.ArrayDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Movie {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("imdbID")
    @EqualsAndHashCode.Include
    private String imdbID;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Genre")
    @JsonDeserialize(using = ArrayDeserializer.class)
    private String[] genre;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Ratings")
    private Rating[] ratings;
    
}
