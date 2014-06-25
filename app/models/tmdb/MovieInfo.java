package models.tmdb;

import java.util.Date;

public class MovieInfo extends VideoInfo {
	
	private String title;
	private String original_title;
	private Date release_date;
	private int runtime;
	
	public MovieInfo () {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "MovieInfo [title=" + title + ", original_title="
				+ original_title + ", release_date=" + release_date
				+ ", runtime=" + runtime + ", toString()="
				+ super.toString() + "]";
	}
	
	


	
	
}
