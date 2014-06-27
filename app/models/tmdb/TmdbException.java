package models.tmdb;

/**
 * @author nicolas
 * Specialized exception class for TMDB calls
 */
public class TmdbException extends Exception {

	/**
	 * no idea
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public TmdbException() {
		super();
	}

	/**
	 * Default constructor
	 */
	public TmdbException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Default constructor
	 */
	public TmdbException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default constructor
	 */
	public TmdbException(String message) {
		super(message);
		
	}

	/**
	 * Default constructor
	 */
	public TmdbException(Throwable cause) {
		super(cause);

	}
}
