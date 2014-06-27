package models.tmdb;

import java.util.Date;
import java.util.List;



/**
 * @author nicolas
 * Specialized VideoInfo class to match specific tv series needs
 *
 */
public class TVInfo extends VideoInfo {
	
	/**
	 * list of runtimes, since some episodes may run longer than others 
	 */
	private List<Integer> episode_run_time;
	
	/**
	 * series original name
	 */
	private String original_name;
	/**
	 * series name
	 */
	private String name;
	/**
	 * the first time the series aired
	 */
	Date first_air_date;
	/**
	 * @return the episode_run_time
	 */
	public List<Integer> getEpisode_run_time() {
		return episode_run_time;
	}
	/**
	 * @param episode_run_time the episode_run_time to set
	 */
	public void setEpisode_run_time(List<Integer> episode_run_time) {
		this.episode_run_time = episode_run_time;
	}
	/**
	 * @return the original_name
	 */
	public String getOriginal_name() {
		return original_name;
	}
	/**
	 * @param original_name the original_name to set
	 */
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the first_air_date
	 */
	public Date getFirst_air_date() {
		return first_air_date;
	}
	/**
	 * @param first_air_date the first_air_date to set
	 */
	public void setFirst_air_date(Date first_air_date) {
		this.first_air_date = first_air_date;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVInfo [episode_run_time=" + episode_run_time
				+ ", original_name=" + original_name + ", name=" + name
				+ ", first_air_date=" + first_air_date + ", toString()="
				+ super.toString() + "]";
	}
}
