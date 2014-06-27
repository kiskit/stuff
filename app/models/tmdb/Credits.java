package models.tmdb;

import java.util.List;

public class Credits {
	private List<CastItem> cast;
	private List<CastItem> crew;
	/**
	 * @return the cast
	 */
	public List<CastItem> getCast() {
		return cast;
	}

	/**
	 * @param cast the cast to set
	 */
	public void setCast(List<CastItem> cast) {
		this.cast = cast;
	}

	/**
	 * @return the crew
	 */
	public List<CastItem> getCrew() {
		return crew;
	}

	/**
	 * @param crew the crew to set
	 */
	public void setCrew(List<CastItem> crew) {
		this.crew = crew;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Credits [cast=" + cast + ", crew=" + crew + "]";
	}

	
}
