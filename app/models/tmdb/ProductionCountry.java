package models.tmdb;

/**
 * @author nicolas
 * Production country for a movie
 */
public class ProductionCountry {
	private String iso_3166_1;
	private String name;
	
	public ProductionCountry() {
		super();
	}
	/**
	 * @return the iso_3166_1
	 */
	public String getIso_3166_1() {
		return iso_3166_1;
	}
	/**
	 * @param iso_3166_1 the iso_3166_1 to set
	 */
	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
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
		return "ProductionCountry [iso_3166_1=" + iso_3166_1 + ", name=" + name
				+ "]";
	}
	
	
}
