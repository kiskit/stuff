package models.tmdb;

import java.util.Date;


/// Mixes title with name and all dates together
public class BasicVideoInfo {
	private Long id;
	
	private Date release_date;
//	private Date first_air_date;
	private String title;
//	private String name;

	public BasicVideoInfo() {
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

	public Date getFirst_air_date() {
		//return first_air_date;
		return this.getRelease_date();
	}

	public void setFirst_air_date(Date first_air_date) {
		//this.first_air_date = first_air_date;
		this.setRelease_date(first_air_date);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		//return name;
		return this.getTitle();
	}

	public void setName(String name) {
		//this.name = name;
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
