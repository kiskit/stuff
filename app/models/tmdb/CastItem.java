package models.tmdb;

/**
 * @author nicolas
 * Class for getting info about cast on TMDB
 */
public class CastItem {
	/**
	 * name of the cast member
	 */
	private String name;
	/**
	 * job of this cast member
	 */
	private String job;
	
	/**
	 * name for this character
	 */
	private String character;
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
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	
	/**
	 * @return the character
	 */
	public String getCharacter() {
		return character;
	}
	/**
	 * @param character the character to set
	 */
	public void setCharacter(String character) {
		this.character = character;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CastItem [name=" + name + ", job=" + job + ", character="
				+ character + "]";
	}
	
}
