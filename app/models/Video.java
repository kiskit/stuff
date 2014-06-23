package models;


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;

import models.tmdb.MovieInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.MovieDb;
import com.omertron.themoviedbapi.results.TmdbResultsList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Entity
public class Video {
	public Video() {
		
	}

	public enum SupportType {DVD, BLURAY};
	public enum ContentType {UNKNOWN, TV, MOVIE};
	public enum StateType {OK, BROKEN, LOST};
	
    public static Finder find = new Finder(Long.class, Video.class);
    private static String key = "c589965ca14962d100212f66a6a2b1c5";

    
    // Get method
    public String getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
           url = new URL(urlToRead);
           conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           while ((line = rd.readLine()) != null) {
              result += line;
           }
           rd.close();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return result;
     }

    public static List<MovieInfo> getMatchingTitles(String title) {
    	String URL = "";
    	
    	//getHTML();
    	return null;
    }
    
    /*
    public static List<MovieDb> getMatchingTitles(String title) {
    	List<MovieDb> list = null;
    	if (api == null)  {
    	   	try {
    	  		api = new TheMovieDbApi(key);
    	   	} catch (MovieDbException e) {
    	   		// TODO Auto-generated catch block
    	   		e.printStackTrace();
    			return null;
    	  	}
    	}
    	System.out.println("Looking for title " + title);
    	try {
    		// Could be Null Pointer Exception
    		TmdbResultsList<MovieDb> tmdblist = api.searchMovie(title, 0, "", true, 0);
    		if (tmdblist != null) {
    			
    			list = tmdblist.getResults();	
    			System.out.println("Results " + list.size());
    		}
    	} catch (MovieDbException e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();    		
    	}
    	return list;
    }
    
    public static MovieDb getInfo(String id) {
 
    	if (api == null)
    	try {
    		api = new TheMovieDbApi(key);
    	} catch (MovieDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
    	}
    	try {
			MovieDb movie = api.getMovieInfo(Integer.parseInt(id), "fr");
			System.out.println(movie.toString());
			return movie;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MovieDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public URL getPoster(MovieDb movie) {
    	try {
    		return getApi().createImageUrl(movie.getPosterPath(), "w185");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    public URL getBackdrop(MovieDb movie) {
    	try {
    		return getApi().createImageUrl(movie.getBackdropPath(), "w780");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    */
	@Id
	private Long id;
	@Constraints.Required
	private SupportType supportType;
	@Constraints.Required
	private ContentType contentType;
	private String movieId;
	@Constraints.Required
	private String inputTitle;
	Long year;
	private String originalTitle;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date creationDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date updateDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    String genres;
    private Date rentalDate;
    private Long rentedTo;
	@Constraints.Required
    private StateType state;
	private Long minimumAge;
    
    private static TheMovieDbApi api = null;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SupportType getSupportType() {
		return supportType;
	}
	public void setSupportType(SupportType supportType) {
		this.supportType = supportType;
	}
	public ContentType getContentType() {
		return contentType;
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getInputTitle() {
		return inputTitle;
	}
	public void setInputTitle(String inputTitle) {
		this.inputTitle = inputTitle;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Long getRentedTo() {
		return rentedTo;
	}
	public void setRentedTo(Long rentedTo) {
		this.rentedTo = rentedTo;
	}
	
	
	public StateType getState() {
		return state;
	}
	public void setState(StateType state) {
		this.state = state;
	}
	
	
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		Video.key = key;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public static TheMovieDbApi getApi() {
		if (api == null) {
			try {
				return new TheMovieDbApi(key);
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
		else
			return api;
	}
	public static void setApi(TheMovieDbApi api) {
		Video.api = api;
	}
	
	public Long getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(Long minimumAge) {
		this.minimumAge = minimumAge;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", supportType=" + supportType
				+ ", contentType=" + contentType + ", movieId=" + movieId
				+ ", inputTitle=" + inputTitle + ", year=" + year
				+ ", originalTitle=" + originalTitle + ", creationDate="
				+ creationDate + ", updateDate=" + updateDate + ", genres="
				+ genres + ", rentalDate=" + rentalDate + ", rentedTo="
				+ rentedTo + ", state=" + state + ", minimumAge=" + minimumAge
				+ "]";
	}

	
}
