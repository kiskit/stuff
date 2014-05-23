package controllers;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.mvc.*;

import views.html.*;
import model.*;
public class Application extends Controller {

    public static Result index() {
    	//ArrayList<Movie> movieList = new ArrayList<Movie>();
    	ArrayList<String> titles = new ArrayList<String>();
    	titles.add("Dragons");
    	titles.add("Cloverfield");
    	titles.add("Titanic");
    	titles.add("Godzilla");
    	titles.add("Eragon");
    	titles.add("Spider");
    	List<Movie> movieList = new ArrayList<Movie>();
    	List<Movie> tMovieList = null;
    	for (int i = 0; i < titles.size(); ++i) {
    		tMovieList = MovieSearch.populateFromRemoteDB(key, titles.get(i));
    		if ((tMovieList != null) && (tMovieList.size() > 0))
    			movieList.add(tMovieList.get(0));
    		else
    			System.out.println("Could not find movie: " + titles.get(i));
    	}
    	
        return ok(mlist.render(movieList));
    }
    public static String key = "c589965ca14962d100212f66a6a2b1c5";
    
}
