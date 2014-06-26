package controllers;

import models.User;

import play.Logger;
import play.mvc.*;
import play.data.Form;
import views.html.*;

/**
 * @author nicolas
 * The Application class deals with high level application things, like logging in 
 *
 */
public class Application extends Controller {
	

	// ----------- Authentication stuff -----------------
	
	/**
	 * The main page for admin login
	 * @return the web page for login
	 */
	public static Result login(){
		return ok(login.render(Form.form(Login.class)));
	}
	
	/**
	 * The validation for the login form
	 * @return The video list web page if validation ok, if not goes back to the login page
	 */
	public static Result authenticate() {
	    Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        Logger.warn("Administrator authentication form validation failed: " + loginForm.get().email);
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("email", loginForm.get().email);
	        Logger.info("Administrator connected: " + loginForm.get().email);
	        return redirect(
	            routes.Videos.index()
	        );
	    }
	}
	
	/**
	 * Logs the user out
	 * @return The login web page 
	 */
	public static Result logout() {
		session().clear();
		// That just doesn't do anything
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}
	
	/**
	 * @author nicolas
	 * Internal class that holds login information
	 *
	 */
	public static class Login{
		public String email;
		public String password;
		public String validate(){
			if (User.authenticate(email, password) == null){
		        Logger.warn("Administrator authentication failed: " + email);
				return "Utilisateur ou mot de passe erroné";
			}
			return null;
		}
		public Login() {
			
		}
		/**
		 * Getter for email
		 * @return email
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * Setter for email
		 * @param email
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * Getter for password
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * Setter for password
		 * @param password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	// ----------- END of authentication -----------------
}

