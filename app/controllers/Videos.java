package controllers;

import models.Borrowing;

import models.Rental;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omertron.themoviedbapi.model.MovieDb;

import controllers.Application.Login;

import play.data.Form;
import play.libs.Json;
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
			vid.setId(13L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Eragon");
			vid.setMovieId("10190");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setState(StateType.OK);
			vid.setRentedTo(2L);
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
			vid.setId(15L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Fight Club");
			vid.setMovieId("10192");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setState(StateType.OK);
			vid.setRentedTo(1L);
			Ebean.save(vid);

			vid = new Video();
			vid.setId(16L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Frasier");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			vid = new Video();
			vid.setId(18L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Rome");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(19L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("24");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
//			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(20L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Modern Family");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);		
			
			vid = new Video();
			vid.setId(21L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Lost");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
			vid.setState(StateType.LOST);
			Ebean.save(vid);
			vid = new Video();
			
			vid.setId(22L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Snake in the eagle's shadow");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
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
		//return ok(videolist.render(Video.find.findList(), new User()));
		return ok(videolist.render(Video.find.findList().size(), new User()));
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

			//return ok(videolist.render(Video.find.findList(), new User()));
			return ok(videolist.render(Video.find.findList().size(), new User()));
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
		//return ok(videolist.render(Video.find.findList(), new User()));
		return ok(videolist.render(Video.find.findList().size(), new User()));
	}
	public static Result borrow() {
		Form<UserChoice> bForm = Form.form(UserChoice.class);
		//Form<UserChoice> longForm =  Form.form(UserChoice.class);
		//		return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		//return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		return ok(videolist.render(Video.find.findList().size(), new User()));
		//return ok(videolist.render(Video.find.findList(), new User()));
	}
	public static Result validateBorrow() {
		Form<UserChoice> bForm = Form.form(UserChoice.class);
		//Form<UserChoice> longForm =  Form.form(UserChoice.class);
		//		return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		//return ok(borrowing.render(bForm, Ebean.find(User.class).findList(), null, new User()));
		//return ok(videolist.render(Video.find.findList(), new User()));
		return ok(videolist.render(Video.find.findList().size(), new User()));
	}
	public static Result getUserRentals(Long userId, Long videoId) {
		List<Video> list = null;
		System.out.println("In get user rentals");
		if (userId != 0) {
			System.out.println("got a user id of " + userId);
			// Get all videos rented by this user
			list = Ebean.find(Video.class).where().eq("rentedTo", userId).findList();
			
		} else if (videoId != 0) {
			System.out.println("got a video id of " + userId);
			// Get the user who rented that video
			Video v = Ebean.find(Video.class, videoId);
			// TODO: test video not found
			userId = v.getRentedTo();
			if (userId == null) {
				list = null;
			} else {
				// Get all videos rented by this user
				list = Ebean.find(Video.class).where().eq("rentedTo", userId).findList();
			}
		}
		Rental rental = new Rental();
		rental.setUserId(userId);
		if (list != null) {
			for (Video rv: list) {
				System.out.println("Adding rental " + userId + " for video " + rv.getId());
				rental.addVideo(rv.getId(), rv.getInputTitle());
			}
		}
		System.out.println("Jason:" + Json.toJson(rental));
		return ok(Json.toJson(rental));
	}
	
	public static Result getVideoList(Integer pageNumber, Integer pageSize) {
		System.out.println("In get video list");
		List<Video> videoList = Ebean.find(Video.class).where().findPagingList(pageSize).getPage(pageNumber - 1).getList();
		System.out.println("List size " + videoList.size());
		return ok(Json.toJson(videoList));
	}
	
	public static class Checkout {
		public Checkout() {
			
		}
	}
	

	public static Result checkin() {
		return ok(checkin.render(User.find.findList(), new User()));
	}
	public static Result doCheckin(String list) {
		String[] parts = list.split(",");
		for (int i = 0; i < parts.length; ++i) {
			Video v = Ebean.find(Video.class, Long.parseLong(parts[i]));
			if (v != null) {
				v.setRentedTo(null);
				v.setRentalDate(null);
				Ebean.save(v);
			} else {
				// TODO: error, a vid was not found
				System.out.println("Video " + parts[i] + " not found");
			}
		}
		return ok(checkin.render(User.find.findList(), new User()));
	}
}