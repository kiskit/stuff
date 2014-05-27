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
		return ok(index.render(""));
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
/*	public static Result index() {

		Video vid = new Video();
		vid.setId(12L);
		vid.setContentType(Video.VideoContentType.MOVIE);
		vid.setSupportType(Video.VideoSupportType.BLURAY);
		vid.setInputTitle("Elysium");
		vid.setCreationDate(new Date());
		vid.setUpdateDate(new Date());
		//vid.save();
		//Ebean.save(vid);
		Video v = Ebean.find(Video.class, 34L);
		if (v == null) {
			System.out.println("Video 34 not found");
		}
		v = Ebean.find(Video.class, 12L);
		if (v == null) {
			System.out.println("Video 12 not found");
		}
		return ok(index.render("Your new application is ready."));
	}
	public static Result user(){
		System.out.println("Displaying user");
		return ok(
				user.render(Form.form(User.class)) 
		);
	}

	public static Result validateUser(){
		Form<User> userForm = Form.form(User.class).bindFromRequest();
		System.out.println("Validating user");

		if(userForm.hasErrors()){
			System.out.println("Baaaad");
			return badRequest(user.render(userForm));
		}
		else{
			System.out.println("Goooood");
			session().clear();
			session("email", userForm.get().getEmail());
			System.out.println(userForm.get().getFirstName());
			System.out.println(userForm.get().getName());
			if (userForm.get().isAdmin())
				System.out.println("is admin");
			else
				System.out.println("is not admin");
			return redirect(controllers.routes.Application.index());
		}
	}

	public static Result insertUser(){
		System.out.println("Inserting user");
		return ok(index.render("Your new application is ready."));
	}
}
 */


/*
 * Controllers
 * 		Application
 * 			login
 * 			admin page
 * 		Users
 * 			User edit
 * 			User list
 * 		Video
 * 			Video list
 * 			Video edit
 * 			Video information populate
 * Forms
 * 	
 */
