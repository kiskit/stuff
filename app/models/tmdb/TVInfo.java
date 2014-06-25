package models.tmdb;

import java.util.Date;
import java.util.List;
public class TVInfo extends VideoInfo {
	private List<Integer> episode_run_time;
	
	private String original_name;
	private String name;
	Date first_air_date;

	
	
	public List<Integer> getEpisode_run_time() {
		return episode_run_time;
	}
	public void setEpisode_run_time(List<Integer> episode_run_time) {
		this.episode_run_time = episode_run_time;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFirst_air_date() {
		return first_air_date;
	}
	public void setFirst_air_date(Date first_air_date) {
		this.first_air_date = first_air_date;
	}
	@Override
	public String toString() {
		return "TVInfo [episode_run_time=" + episode_run_time
				+ ", original_name=" + original_name + ", name=" + name
				+ ", first_air_date=" + first_air_date + ", toString()="
				+ super.toString() + "]";
	}

	

}
