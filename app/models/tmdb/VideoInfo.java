package models.tmdb;

import java.util.List;

/**
 * @author nicolas
 * The information common to tv series and movies as per the TMDB API
 */
public class VideoInfo {
	/**
	 * path to the video's backdrop
	 */
	private String backdrop_path;
	/**
	 * path to the video's poster
	 */
	private String poster_path;
	/**
	 * overview for this video
	 */
	private String overview;
	/**
	 * vote average (rating) for this video
	 */
	private double vote_average;
	/**
	 * list of genres (drama, adventure) for this video
	 */
	private List<Genre> genres;
	/**
	 * tagline for this video (in space, no one will hear you scream)
	 */
	String tagline;
	/**
	 *  who played in this movie
	 *  Note: this is not directly given by the movie query on TMDB
	 */
	private String actors;

	
	
	/**
	 * Default constructor
	 */
	public VideoInfo() {
		
	}

	/**
	 * @return the backdrop_path
	 */
	public String getBackdrop_path() {
		return backdrop_path;
	}

	/**
	 * @param backdrop_path the backdrop_path to set
	 */
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	/**
	 * @return the poster_path
	 */
	public String getPoster_path() {
		return poster_path;
	}

	/**
	 * @param poster_path the poster_path to set
	 */
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	/**
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * @param overview the overview to set
	 */
	public void setOverview(String overview) {
		this.overview = overview;
	}


	/**
	 * @return the vote_average
	 */
	public double getVote_average() {
		return vote_average;
	}

	/**
	 * @param vote_average the vote_average to set
	 */
	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VideoInfo [backdrop_path=" + backdrop_path + ", poster_path="
				+ poster_path + ", overview=" + overview 
				+ ", vote_average=" + vote_average + ", genres="
				+ genres + ", tagline=" + tagline + ", toString()="
				+ super.toString() + "]";
	}

}
