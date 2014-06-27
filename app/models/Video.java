package models;


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model.Finder;
//import play.db.ebean.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import models.tmdb.MovieInfo;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Video {
	public Video() {
		picturePath = tmdbPicturesPath;
	}

	public enum SupportType {DVD, BLURAY};
	public enum ContentType {UNKNOWN, TV, MOVIE};
	public enum StateType {OK, BROKEN, LOST};

	public static Finder find = new Finder(Long.class, Video.class);
	private static String tmdbPicturesPath = "http://image.tmdb.org/t/p/w500";

	@Id
	private Long id;
	@Constraints.Required
	private SupportType supportType;
	@Constraints.Required
	private ContentType contentType;
	private String movieId;
	@Constraints.Required
	private String inputTitle;
	private Long year;
	private String directors;
	private String countries;
	private String actors;
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
	double rating;
	private String posterPath;
	private String backdropPath;
	@Lob
	private String summary;
	private String tagline;
	private int runtime;
	private String picturePath;
	
	

	
	/**
	 * @return a readable string of who rented the video and when
	 */
	public String getRentalString() {
		String rentalString = null;
		if (rentedTo != null) {
			User u = Ebean.find(User.class, rentedTo);
			if (u != null) {
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				rentalString = u.getFullName() + " (" + sdf.format(rentalDate) + ")";
			}
		}
		return rentalString;
	}
	/**
	 * @return the user object corresponding to the user to whom the video was rented
	 */
	public User getRentedToUser() {
		return rentedTo == null?null:Ebean.find(User.class, rentedTo);
	}


	
	/**
	 * @return the director
	 */
	public String getDirectors() {
		return directors;
	}
	/**
	 * @param director the director to set
	 */
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	
	/**
	 * @return the actors
	 */
	public String getActors() {
		return actors;
	}
	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		this.actors = actors;
	}
	/**
	 * @return the countries
	 */
	public String getCountries() {
		return countries;
	}
	/**
	 * @param countries the countries to set
	 */
	public void setCountries(String countries) {
		this.countries = countries;
	}
	/**
	 * @return the path to the pictures on themoviedb.org
	 */
	public String getPicturePath() {
		return tmdbPicturesPath;
	}
	/**
	 * @param picturePath
	 * This method sets the picture path in the object. However the getter will return the constant value of the path to the pictures on themoviedb.org 
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	
	
	/**
	 * @return the tmdbPicturesPath
	 */
	public static String getTmdbPicturesPath() {
		return tmdbPicturesPath;
	}
	/**
	 * @param tmdbPicturesPath the tmdbPicturesPath to set
	 */
	public static void setTmdbPicturesPath(String tmdbPicturesPath) {
		Video.tmdbPicturesPath = tmdbPicturesPath;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the supportType
	 */
	public SupportType getSupportType() {
		return supportType;
	}
	/**
	 * @param supportType the supportType to set
	 */
	public void setSupportType(SupportType supportType) {
		this.supportType = supportType;
	}
	/**
	 * @return the contentType
	 */
	public ContentType getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the movieId
	 */
	public String getMovieId() {
		return movieId;
	}
	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	/**
	 * @return the inputTitle
	 */
	public String getInputTitle() {
		return inputTitle;
	}
	/**
	 * @param inputTitle the inputTitle to set
	 */
	public void setInputTitle(String inputTitle) {
		this.inputTitle = inputTitle;
	}
	/**
	 * @return the year
	 */
	public Long getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Long year) {
		this.year = year;
	}
	/**
	 * @return the originalTitle
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}
	/**
	 * @param originalTitle the originalTitle to set
	 */
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the genres
	 */
	public String getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(String genres) {
		this.genres = genres;
	}
	/**
	 * @return the rentalDate
	 */
	public Date getRentalDate() {
		return rentalDate;
	}
	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	/**
	 * @return the rentedTo
	 */
	public Long getRentedTo() {
		return rentedTo;
	}
	/**
	 * @param rentedTo the rentedTo to set
	 */
	public void setRentedTo(Long rentedTo) {
		this.rentedTo = rentedTo;
	}
	/**
	 * @return the state
	 */
	public StateType getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(StateType state) {
		this.state = state;
	}
	/**
	 * @return the minimumAge
	 */
	public Long getMinimumAge() {
		return minimumAge;
	}
	/**
	 * @param minimumAge the minimumAge to set
	 */
	public void setMinimumAge(Long minimumAge) {
		this.minimumAge = minimumAge;
	}
	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	/**
	 * @return the posterPath
	 */
	public String getPosterPath() {
		return posterPath;
	}
	/**
	 * @param posterPath the posterPath to set
	 */
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	/**
	 * @return the backdropPath
	 */
	public String getBackdropPath() {
		return backdropPath;
	}
	/**
	 * @param backdropPath the backdropPath to set
	 */
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}
	/**
	 * @param tagline the tagline to set
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	/**
	 * @return the runtime
	 */
	public int getRuntime() {
		return runtime;
	}
	/**
	 * @param runtime the runtime to set
	 */
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Video [id=" + id + ", supportType=" + supportType
				+ ", contentType=" + contentType + ", movieId=" + movieId
				+ ", inputTitle=" + inputTitle + ", year=" + year
				+ ", directors=" + directors + ", countries=" + countries
				+ ", actors=" + actors + ", originalTitle=" + originalTitle
				+ ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + ", genres=" + genres + ", rentalDate="
				+ rentalDate + ", rentedTo=" + rentedTo + ", state=" + state
				+ ", minimumAge=" + minimumAge + ", rating=" + rating
				+ ", posterPath=" + posterPath + ", backdropPath="
				+ backdropPath + ", summary=" + summary + ", tagline="
				+ tagline + ", runtime=" + runtime + ", picturePath="
				+ picturePath + "]";
	}

}
