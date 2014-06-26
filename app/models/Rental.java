package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

/**
 * @author nicolas
 * Utility class designed to be returned for Ajax calls. Pretty self explanatory
 */
public class Rental {
	
	/**
	 * Default constructor 
	 */
	public Rental() {
	
	
	}
	
	/**
	 * The list of current rentals with who, what and when
	 * @return a list of all rentals with fields populated in the right fashion
	 */
	public static List<Rental> getAllRentals() {
		List<Rental> rentals = new ArrayList<Rental>();
		List<Video> videos = Ebean.find(Video.class).where().isNotNull("rentedTo").findList();
		Ebean.sort(videos, "rentalDate asc");
		Rental rental = null;
		User user = null;
		for (Video v : videos) {
			rental = new Rental();
			rental.setWhatId(v.getId());
			rental.setWhat(v.getInputTitle());
			user = Ebean.find(User.class, v.getRentedTo());
			rental.setWho(user.getFullName());
			rental.setWhoId(v.getRentedTo());
			rental.setEmail(user.getEmail());
			rental.setWhen(v.getRentalDate());
			rentals.add(rental);
		}
		return rentals;
	}
	/**
	 * Used to obtain a list of rentals for a specific user
	 * @param userId the user whose rentals we're interested into
	 * @return the list of rentals for the user
	 */
	public static List<Rental> getRentalsByUserId(Long userId) {
		List<Rental> rentals = new ArrayList<Rental>();
		List<Video> videos = Ebean.find(Video.class).where().eq("rentedTo", userId).findList();
		Rental rental = null;
		User user = null;
		for (Video v : videos) {
			rental = new Rental();
			rental.setWhatId(v.getId());
			rental.setWhat(v.getInputTitle());
			user = Ebean.find(User.class, v.getRentedTo());
			rental.setWho(user.getFullName());
			rental.setWhoId(v.getRentedTo());
			rental.setEmail(user.getEmail());
			rental.setWhen(v.getRentalDate());
			rentals.add(rental);
		}
		return rentals;
	}
	private Long whoId;
	private Long whatId;
	private String who;
	private String what;
	private Date when;
	private String email;
	
	

	/**
	 * @return the whoId
	 */
	public Long getWhoId() {
		return whoId;
	}

	/**
	 * @param whoId the whoId to set
	 */
	public void setWhoId(Long whoId) {
		this.whoId = whoId;
	}

	/**
	 * @return the whatId
	 */
	public Long getWhatId() {
		return whatId;
	}

	/**
	 * @param whatId the whatId to set
	 */
	public void setWhatId(Long whatId) {
		this.whatId = whatId;
	}

	/**
	 * @return the who
	 */
	public String getWho() {
		return who;
	}

	/**
	 * @param who the who to set
	 */
	public void setWho(String who) {
		this.who = who;
	}

	/**
	 * @return the what
	 */
	public String getWhat() {
		return what;
	}

	/**
	 * @param what the what to set
	 */
	public void setWhat(String what) {
		this.what = what;
	}

	/**
	 * @return the when
	 */
	public Date getWhen() {
		return when;
	}

	/**
	 * @param when the when to set
	 */
	public void setWhen(Date when) {
		this.when = when;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rental [whoId=" + whoId + ", whatId=" + whatId + ", who=" + who
				+ ", what=" + what + ", when=" + when + ", email=" + email
				+ "]";
	}




	
	
}
