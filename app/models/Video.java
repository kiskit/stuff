package models;


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Entity
public class Video {
	public Video() {
		
	}

	public enum SupportType {DVD, BLURAY};
	public enum ContentType {UNKNOWN, TV, MOVIE};
	public enum StateType {OK, BROKEN, LOST};
	
    public static Finder find = new Finder(Long.class, Video.class);
    private static String key = "c589965ca14962d100212f66a6a2b1c5";

    public static MovieInfo getInfo(String id) {
    	//String url = "https://api.themoviedb.org/3/search/movie?api_key="+key+"&query=" + title;
    	String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key="+key+"&language=fr";
		URL obj;
		try {
			obj = new URL(url);

			System.out.println("URL:" + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response);
			in.close();
			System.out.println("Mapping objects");			
			ObjectMapper objectMapper = new ObjectMapper();
			//		movies = objectMapper.readValue(response.toString(), new TypeReference<List<Movie>>(){});
			//movies = new ArrayList<Movie>();
			MovieInfo info = objectMapper.readValue(response.toString(), MovieInfo.class);
			if (info != null) {
				System.out.println("Info read" + info.toString());
			} else {
				System.out.println("No info read");
			}
			return info;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}

    	return null;
    }
    
	@Id
	private Long id;
	@Constraints.Required
	private SupportType supportType;
	@Constraints.Required
	private ContentType contentType;
	private String movieId;
	@Constraints.Required
	private String inputTitle;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date creationDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date updateDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date rentalDate;
    private Long rentedTo;
	@Constraints.Required
    private StateType state;
    
    
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
	@Override
	public String toString() {
		return "Video [id=" + id + ", supportType=" + supportType
				+ ", contentType=" + contentType + ", movieId=" + movieId
				+ ", inputTitle=" + inputTitle + ", creationDate="
				+ creationDate + ", updateDate=" + updateDate + ", rentedTo="
				+ rentedTo + ", state=" + state + "]";
	}


}
/*
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieSearch {
	MovieSearch() {

	}



	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Movie> getResults() {
		return results;
	}
	public void setResults(List<Movie> results) {
		this.results = results;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

	public static List<Movie> populateFromRemoteDB(String key, String title) {

		List<Movie> movies = null;
		MovieSearch search = null;
		String url = "https://api.themoviedb.org/3/search/movie?api_key="+key+"&query=" + title; 
		URL obj;
		try {
			obj = new URL(url);

			System.out.println("URL:" + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response);
			in.close();
			System.out.println("Mapping objects");			
			ObjectMapper objectMapper = new ObjectMapper();
			//		movies = objectMapper.readValue(response.toString(), new TypeReference<List<Movie>>(){});
			//movies = new ArrayList<Movie>();
			search = objectMapper.readValue(response.toString(), MovieSearch.class);
			if (search != null) {
				movies = search.getResults();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (movies);
	}

	int page;
	List <Movie> results;
	int total_pages;
	int total_results;
}
*/