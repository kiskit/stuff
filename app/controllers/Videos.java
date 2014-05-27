package controllers;

import models.User;
import models.Video;
import models.Video.StateType;
import play.mvc.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import controllers.Application.Login;

import play.data.Form;
import play.libs.Yaml;
import views.html.*;
import java.util.Date;
import java.util.List;

public class Videos extends Controller {

	public static Result index() {
		// Call the video list with all the videos
		//return ok(index.render(Video.find.all()));
		Video vid = vid = Ebean.find(Video.class).where().eq("id", 12L).findUnique();
		if (vid == null) {
			vid = new Video();

			vid.setId(12L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.BLURAY);
			vid.setInputTitle("Elysium");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(null);
			vid.setState(StateType.OK);
			Ebean.save(vid);

			vid = new Video();
			vid.setId(14L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Dragons");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setState(StateType.OK);
			Ebean.save(vid);

			vid = new Video();
			vid.setId(18L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Rome");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(18L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
		}

		return ok(videolist.render(Video.find.findList(), new User()));
		//return ok(videolist.render(Video.find.findList(), null));

	}

	public static Result editVideo(Long id) {
		System.out.println("Editing video " + id);
		/**
		 * If the Video already exists, we can look for it in the database
		 * We will populate all the fields with it
		 * If it does't exist, we are creating a new one
		 */

		Video v = Ebean.find(Video.class, id);
		if (v == null) {
			// Video not found, creating
			v = new Video();
			v.setCreationDate(new Date());
			String sql = "select max(id) from Video";
			SqlRow bug = Ebean.createSqlQuery(sql).findUnique();
			System.out.println("Bug :" + bug.toString());
			String maxId = bug.getString("max(id)");
			System.out.println("Max ID found : " + maxId);
			v.setId(Long.decode(maxId) + 1L);
		}
		Form<Video> videoForm = Form.form(Video.class);
		v.setUpdateDate(new Date());

		videoForm = videoForm.fill(v);
		return ok(videoedit.render(videoForm));
	}
	public static Result validateVideo() {
		Form<Video> videoForm = Form.form(Video.class).bindFromRequest();
		if(videoForm.hasErrors()) {
			System.out.println("Bad request Validating video");
			return badRequest(videoedit.render(videoForm));
		} else {
			if (Ebean.find(Video.class, videoForm.get().getId()) != null) {
				System.out.println("Updating video " + videoForm.toString());
				Ebean.update(videoForm.get());	
			} else {
				System.out.println("Inserting video" + videoForm.toString());
				Ebean.save(videoForm.get());
			}
			
			return ok(videolist.render(Video.find.findList(), new User()));
		}

	}
	//public static Result 

}

/*
	@helper.input(videoForm("supportType"), '_label -> "Type de support") { (id, name, value, args) =>
    		<select>
    			<option value="DVD">DVD</option>
    		 	<option value="BLURAY">Blu-Ray</option>
    		</select> 
    	}


 	@helper.input(videoForm("inputTitle"), '_label -> "Titre vidéo", '_showConstraints -> false) { (id, name, value, args) =>
        		<input type="text" class="form-control" value="nimportequoi"  required autofocus>
        	}    	

 */
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
