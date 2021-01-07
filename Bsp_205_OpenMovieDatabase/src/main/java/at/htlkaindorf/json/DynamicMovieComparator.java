package at.htlkaindorf.json;

import at.htlkaindorf.pojos.Movie;
import beans.Sortings;
import java.util.Comparator;
import java.util.List;

public class DynamicMovieComparator implements Comparator<Movie>{

    private List<Sortings> sort;

    public DynamicMovieComparator(List<Sortings> sort) {
        this.sort = sort;
    }
    
    @Override
    public int compare(Movie o1, Movie o2) {
        int res = 0;
        
        for(Sortings s : sort) {
            switch(s) {
                case TITLE: res = o1.getTitle().compareTo(o2.getTitle()); break;
                case DATE: res = o1.getYear().compareTo(o2.getYear()); break;
            }
                        
            if(res != 0)
                break;
        }
        
        return res;
    }

}
