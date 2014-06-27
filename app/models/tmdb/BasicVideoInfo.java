package models.tmdb;

import java.util.Date;

/**
 * @author nicolas
 * When looking for information on TMDB, one gets almost the same info for tv series and movies. However, Json fields differ
 * This class implements the basic fields for both, and hides the differences under common names so as to make json calls easier
 * name and title are factorized into title
 * release_date and first_air_date are factorized into release_date
 * Accessors are written for all Json possible fields although they point to the same members in the class
 */
public class BasicVideoInfo {
	/**
	 * the TMDB id for this video
	 */
	private Long id;
	/**
	 * the release date (or first air date) for this video
	 */
	private Date release_date;
	/**
	 * the title (or name) for this video
	 */
	private String title;

	/**
	 * Default constructor
	 */
	public BasicVideoInfo() {
	}

	/**
	 * @return the id for this video
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * set the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the release date for this video
	 */
	public Date getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date
	 * set the release date for this video
	 */
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	/**
	 * @return the first_air_date for this video
	 */
	public Date getFirst_air_date() {
		return this.getRelease_date();
	}

	/**
	 * set the first air date for this video
	 * @param first_air_date
	 */
	public void setFirst_air_date(Date first_air_date) {
		//this.first_air_date = first_air_date;
		this.setRelease_date(first_air_date);
	}

	/**
	 * @return the title for this video
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 * set the title for this video
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the name of this video
	 */
	public String getName() {
		return this.getTitle();
	}

	/**
	 * @param name
	 * set the name for this video
	 */
	public void setName(String name) {
		this.setTitle(name);
	}

	@Override
	public String toString() {
		return "BasicVideoInfo [id=" + id + ", release_date=" + release_date
				+ ", first_air_date=" + release_date + ", title=" + title
				//+ ", name=" + name + "]";
				+ ", name=" + title + "]";
	}
		
}
