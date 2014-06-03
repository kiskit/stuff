package controllers;

import models.Borrowing;

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
import com.omertron.themoviedbapi.model.MovieDb;

import controllers.Application.Login;

import play.data.Form;
import play.libs.Yaml;
import views.html.*;
import java.util.Date;
import java.util.List;

public class Videos extends Controller {

	public static class UserChoice {
		public UserChoice() {

		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long id;
	}

	public static Result index() {
		// Call the video list with all the videos
		//return ok(index.render(Video.find.all()));
		Video vid = Ebean.find(Video.class).where().eq("id", 12L).findUnique();
		if (vid == null) {
			vid = new Video();

			vid.setId(12L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.BLURAY);
			vid.setInputTitle("Elysium");
			vid.setMovieId("68724");
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
			vid.setMovieId("10191");
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
		User u = Ebean.find(User.class).where().eq("id", 1).findUnique();
		if (u == null) {
			u = new User();

			u.setId(1L);
			u.setName("Pinatel");
			u.setFirstName("Boris");
			u.setAdmin(true);
			u.setCreationDate(new Date());
			u.setUpdateDate(new Date());
			u.setEmail(u.getFirstName() + "." + u.getName() + "@orange.com");
			u.setPassword(u.getFirstName());
			Ebean.save(u);
			u = new User();
			u.setId(2L);
			u.setName("Bellardie");
			u.setFirstName("Nicolas");
			u.setAdmin(true);
			u.setCreationDate(new Date());
			u.setUpdateDate(new Date());
			u.setEmail(u.getFirstName() + "." + u.getName() + "@orange.com");
			u.setPassword(u.getFirstName());
			Ebean.save(u);
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
			
			String[] postAction = request().body().asFormUrlEncoded().get("action");
			if (postAction == null || postAction.length == 0) {
				return badRequest("You must provide a valid action");
			} else {
				String action = postAction[0];

				if ("Cancel".equals(action)) {
					// Returns to main videolist page
					return index();
				}
			}
			
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
	public static Result videoInfo(Long id) {
		Video v = Ebean.find(Video.class, id);
		String idVideo = v.getMovieId();
		MovieDb info = Video.getInfo(idVideo);
		try {
			System.out.println("Info path: " + info.getPosterPath());
			System.out.println("Image path: " + v.getApi().createImageUrl(info.getPosterPath(), "w185"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(videoinfo.render(v, info, new User()));
	}
	public static Result validateVideoInfo() {
		return ok(videolist.render(Video.find.findList(), new User()));
	}
	public static Result borrow() {
		Form<UserChoice> bForm = Form.form(UserChoice.class);
		//Form<UserChoice> longForm =  Form.form(UserChoice.class);
		//		return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		//return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		return ok(videolist.render(Video.find.findList(), new User()));
	}
	public static Result validateBorrow() {
		Form<UserChoice> bForm = Form.form(UserChoice.class);
		//Form<UserChoice> longForm =  Form.form(UserChoice.class);
		//		return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		//return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		return ok(videolist.render(Video.find.findList(), new User()));
	}
	public static class Checkout {
		public Checkout() {
			
		}
	}
}