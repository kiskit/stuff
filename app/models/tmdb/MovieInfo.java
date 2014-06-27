package models.tmdb;

import java.util.Date;
import java.util.List;

/**
 * @author nicolas
 * Specialized VideoInfo class to match specific movies needs
 */
public class MovieInfo extends VideoInfo {
	
	/**
	 * the title of the movie
	 */
	private String title;
	/**
	 * the original title of the movie
	 */
	private String original_title;
	/**
	 * the release date for this movie
	 */
	private Date release_date;
	/**
	 * the movie's runtime in minutes
	 */
	private int runtime;
	/**
	 *  who directed this movie
	 *  Note: this is not directly given by the movie query on TMDB
	 */
	private String directors;
	/**
	 * countries of origin for this movie
	 */
	private List<ProductionCountry> production_countries;
	
	
	/**
	 * Default constructor
	 */
	public MovieInfo () {
		
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the original_title
	 */
	public String getOriginal_title() {
		return original_title;
	}

	/**
	 * @param original_title the original_title to set
	 */
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
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

	/**
	 * @return the production_countries
	 */
	public List<ProductionCountry> getProduction_countries() {
		return production_countries;
	}

	/**
	 * @param production_countries the production_countries to set
	 */
	public void setProduction_countries(List<ProductionCountry> production_countries) {
		this.production_countries = production_countries;
	}

	
	/**
	 * @return the directors
	 */
	public String getDirectors() {
		return directors;
	}

	/**
	 * @param directors the directors to set
	 */
	public void setDirectors(String directors) {
		this.directors = directors;
	}

	/**
	 * @return the release_date
	 */
	public Date getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MovieInfo [title=" + title + ", original_title="
				+ original_title + ", release_date=" + release_date
				+ ", runtime=" + runtime + ", directors=" + directors
				+ ", production_countries=" + production_countries
				+ ", toString()=" + super.toString() + "]";
	}



}
