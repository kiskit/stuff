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
	double rating;
	private String posterPath;
	private String backdropPath;
	@Lob
	private String summary;
	private String tagline;
	private int runtime;
	private String picturePath;
	
	

	
	public String getRentalString() {
		String rentalString = null;
		if (rentedTo != null) {
			User u = Ebean.find(User.class, rentedTo);
			if (u != null)
				rentalString = u.getFullName() + " (" + rentalDate.toLocaleString() + ")";
		}
		return rentalString;
	}
	public User getRentedToUser() {
		return rentedTo == null?null:Ebean.find(User.class, rentedTo);
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBackdropPath() {
		return backdropPath;
	}
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getPosterPath() {
		return posterPath;			
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
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
		if ((rentedTo != null) && (rentedTo < 0)) {
			this.rentedTo = null;
			this.rentalDate = null;
		} else {
			System.out.println("In setter setting to " + rentedTo);
			this.rentedTo = rentedTo;	
		}
		
	}


	public StateType getState() {
		return state;
	}
	public void setState(StateType state) {
		this.state = state;
	}


	public static String getKey() {
		//return key;
		return null;
	}
	public static void setKey(String key) {
		//Video.key = key;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
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

	public String getPicturePath() {
		return tmdbPicturesPath;
		//return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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
				+ ", rating=" + rating + ", posterPath=" + posterPath
				+ ", backdropPath=" + backdropPath + ", summary=" + summary
				+ ", tagline=" + tagline + ", runtime=" + runtime
				+ ", picturePath=" + getPicturePath() + "]";
	}
	
}
