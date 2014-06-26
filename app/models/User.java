package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import play.data.format.Formats;
import play.data.validation.Constraints;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;

@Entity
public class User {
	public enum StatusType {ACTIVE, INACTIVE};

	@Id
	private Long id;
	@Constraints.Required
	private String name;
	@Constraints.Required
	private String firstName;
	@Constraints.Required
	private String email;
	@Constraints.Required
	private String password;
	@Constraints.Required
	private StatusType status;
	@Constraints.Required
	@Formats.DateTime(pattern = "MM/dd/yy")
	private Date creationDate;
	@Constraints.Required
	@Formats.DateTime(pattern = "MM/dd/yy")
	private Date updateDate;
	@Constraints.Required
	private boolean admin;
    public static Finder find = new Finder(Long.class, User.class);

	public User() {
		
	}
	
	/**
	 * @param email
	 * @param password
	 * @return whether the user is authenticated by the email and password provided
	 */
	public static User authenticate(String email, String password) {
		User user = Ebean.find(User.class).where().ieq("email", email).eq("password", password).findUnique();
		return user;
	}

	/**
	 * @param email
	 * @return the user corresponding to the email provided
	 */
	public static User getByEmail(String email) {
		return Ebean.find(User.class).where().ieq("email", email).findUnique();
	}

	/**
	 * @return the user's full name
	 */
	public String getFullName() {
		return getFirstName() + " " + getName();
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstName=" + firstName
				+ ", email=" + email + ", password=" + password + ", status="
				+ status + ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + ", admin=" + admin + "]";
	}
	

}
