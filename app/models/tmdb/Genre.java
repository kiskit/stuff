package models.tmdb;

/**
 * @author nicolas
 * A class to hold genres as defined by TMDB
 */
public class Genre extends IdNamePair {
	public Genre () {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genre [toString()=" + super.toString() + "]";
	}
}
