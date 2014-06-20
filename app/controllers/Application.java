package controllers;

import models.User;
import models.Video;
import play.mvc.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import play.data.Form;
import play.libs.Yaml;
import views.html.*;
import views.html.helper.form;

import java.util.Date;
import play.data.*;

public class Application extends Controller {
	

	// ----------- Authentication stuff -----------------
	
	public static Result login(){
		return ok(login.render(Form.form(Login.class)));
	}
	
	public static Result authenticate() {
	    Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	    	System.out.println("Form still has errors");
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("email", loginForm.get().email);
	        System.out.println("Got the right login");
	        return redirect(
	            routes.Application.index()
	        );
	    }
	}
	
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}
	
	public static class Login{
		public String email;
		public String password;
		public String validate(){
			System.out.println("authenticating with email=" + email + " and password=" + password);
			if (User.authenticate(email, password) == null){
				return "Utilisateur ou mot de passe erroné";
			}
			System.out.println("authenticated");
			return "success";
		}
		public Login() {
			
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
		
	}
	// ----------- END of authentication -----------------
	
	/** 
	 * Administration index
	*/
	//@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}
	public static Result admin() {

		String[] postAction = request().body().asFormUrlEncoded().get("action");
		if (postAction == null || postAction.length == 0) {
			return badRequest("You must provide a valid action");
		} else {
			String action = postAction[0];

			if ("Users".equals(action)) {
				// Returns to main videolist page
				return Users.index();

			} else if ("Videos".equals(action)) {
				return Videos.index();

			} else if ("Late".equals(action)) {
				return ok(index.render("Your new application is ready."));
				
			} else if ("Checkin".equals(action)) {
				return Videos.checkin();
				
			} else if ("Checkout".equals(action)) {
				return Videos.checkout();
				
			} else {
				// TODO: major problem, someone clicked on a non existent button
				return ok(index.render("Your new application is ready."));

			}
		}
		//return ok(index.render("Your new application is ready."));
	}
	
	

}

