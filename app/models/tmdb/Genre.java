package models.tmdb;

/**
 * @author nicolas
 * A class to hold genres as defined by TMDB
 */
public class Genre {


	/**
	 * the genre id
	 */
	private int id;
	/**
	 * the genre name
	 */
	private String name;

	/**
	 * Default constructor 
	 */
	public Genre () {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}




}
