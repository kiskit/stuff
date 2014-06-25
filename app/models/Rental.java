package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

public class Rental {
	
	public Rental() {
	
	
	}
	
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
	
	
	public Long getWhoId() {
		return whoId;
	}

	public void setWhoId(Long whoId) {
		this.whoId = whoId;
	}

	public Long getWhatId() {
		return whatId;
	}

	public void setWhatId(Long whatId) {
		this.whatId = whatId;
	}

	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Rental [whoId=" + whoId + ", whatId=" + whatId + ", who=" + who
				+ ", what=" + what + ", when=" + when + ", email=" + email
				+ "]";
	}


	
	
}
