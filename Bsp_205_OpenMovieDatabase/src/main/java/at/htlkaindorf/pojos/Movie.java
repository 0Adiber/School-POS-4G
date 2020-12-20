package at.htlkaindorf.pojos;

import at.htlkaindorf.json.ArrayDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonProperty("Title")
    private String title;
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
    private String imdbID;
    @JsonProperty("Director")
    @JsonDeserialize(using = ArrayDeserializer.class)
    private String[] director;
    @JsonProperty("Genre")
    @JsonDeserialize(using = ArrayDeserializer.class)
    private String[] genre;
    @JsonProperty("Writer")
    @JsonDeserialize(using = ArrayDeserializer.class)
    private String[] writer;
    @JsonProperty("Actors")
    @JsonDeserialize(using = ArrayDeserializer.class)
    private String[] actors;
    @JsonProperty("Ratings")
    private Rating[] ratings;

}
