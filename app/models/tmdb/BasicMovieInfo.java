package models.tmdb;

import java.util.Date;

public class BasicMovieInfo {
	private Long id;
	
	private Date release_date;
	private String title;
	/*
	private double vote_average;
	private String original_title;
	private Long vote_count;
	private boolean adult;
	private String backdrop_path;
	private String poster_path;
	private double popularity;
	*/

	public BasicMovieInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/*
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}	public double getVote_average() {
		return vote_average;
	}
	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}
	public Long getVote_count() {
		return vote_count;
	}
	public void setVote_count(Long vote_count) {
		this.vote_count = vote_count;
	}
	@Override
	public String toString() {
		return "BasicMovieInfo [adult=" + adult + ", backdrop_path="
				+ backdrop_path + ", id=" + id + ", original_title="
				+ original_title + ", release_date=" + release_date
				+ ", poster_path=" + poster_path + ", popularity=" + popularity
				+ ", title=" + title + ", vote_average=" + vote_average
				+ ", vote_count=" + vote_count + "]";
	}
	*/
	@Override
	public String toString() {
		return "BasicMovieInfo [id=" + id + ", release_date=" + release_date
				+ ", title=" + title + "]";
	}
	
	
	
}
