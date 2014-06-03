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
	
	/** 
	 * Administration index
	*/
	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}
	
	
	// HAVE A LOOK AT THE SECURE PACKAGE
	public static Result login(){
		System.out.println("Displaying login page");
		return ok(login.render(Form.form(Login.class)));
	}
	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if(loginForm.hasErrors()) {
			//return badRequest(form.render(loginForm));
			return badRequest();
		} else {
			System.out.println("authenticating");
			User user = User.authenticate(loginForm.get().email, loginForm.get().password);
			return ok(index.render("Your new application is ready."));
		}
	}
	public static Result logout() {
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
				return ok(index.render("Your new application is ready."));
				
			} else {
				// TODO: major problem, someone clicked on a non existent button
				return ok(index.render("Your new application is ready."));

			}
		}
		//return ok(index.render("Your new application is ready."));
	}
	
	
	public static class Login{
		public String email;
		public String password;
		public boolean rememberMe;
		public String validate(){
			if (User.authenticate(email, password) == null){
				return "Invalid user or password";
			}
			return null;
		}
	}
}

