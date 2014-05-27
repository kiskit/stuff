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
	private boolean isAdmin;
	
    public static Finder find = new Finder(Long.class, User.class);

	
	public User() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public StatusType getStatus() {
		return status;
	}



	public void setStatus(StatusType status) {
		this.status = status;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public boolean setIsAdmin() {
		return isAdmin;
	}



	public void getIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstName=" + firstName
				+ ", email=" + email + ", password=" + password + ", status="
				+ status + ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + ", isAdmin=" + isAdmin + "]";
	}
	
	public String getFullName() {
		return getFirstName() + " " + getName();
	}
	
	public String validate() {
		return null;
	}
	// Not sure what it does
	//public static Finder<String,User> find = new Finder<String,User>(String.class, User.class); 
	public static User authenticate(String email, String password) {
		// 
		User user = Ebean.find(User.class).where().eq("email", email)
        .eq("password", password).findUnique();
		List<User> users = Ebean.find(User.class).where().eq("email", email).findList();
		
		// Check for one user in the database with the right login and password
		if (user != null) {
			System.out.println("User found");	
		}
		else  {
			System.out.println("User not found");
		}
		return user;
	}
}
