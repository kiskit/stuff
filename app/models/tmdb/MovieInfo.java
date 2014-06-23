package models.tmdb;

import java.util.Date;

//import com.fasterxml.jackson.*;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieInfo {
	static MovieInfo getMovieFromId(Long id) {
		return null;
	}
	
	private String backdrop_path;
	private String poster_path;
	private String overview;
	private String original_title;
	private Date release_date;
	private double vote_average;
	private int runtime;
	
	
	public MovieInfo () {
		
	}
	
	
	
	public int getRuntime() {
		return runtime;
	}



	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}



	public String getOriginal_title() {
		return original_title;
	}



	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}



	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public double getVote_average() {
		return vote_average;
	}
	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}



	@Override
	public String toString() {
		return "MovieInfo [backdrop_path=" + backdrop_path + ", poster_path="
				+ poster_path + ", overview=" + overview + ", original_title="
				+ original_title + ", release_date=" + release_date
				+ ", vote_average=" + vote_average + ", runtime=" + runtime
				+ "]";
	}

	
	
}
