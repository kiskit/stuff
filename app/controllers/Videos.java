package controllers;

import models.Rental;
import models.User;
import models.Video;
import models.tmdb.Genre;
import models.tmdb.IdNamePair;
import models.tmdb.MovieInfo;
import models.tmdb.ProductionCountry;
import models.tmdb.TVInfo;
import models.tmdb.TmdbApi;
import models.tmdb.BasicVideoInfoSearch;
import models.tmdb.VideoInfo;

import play.Logger;
import play.mvc.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;

import play.data.Form;
import play.libs.Json;
import views.html.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.lang.Math;


/**
 * @author nicolas
 * This controller manages everything related to video lists and edition
 */
public class Videos extends Controller {

	/**
	 * The main page for consulting the list of videos
	 * @return the video list page
	 * Note that it's not necessary to be logged in to view the page. However, admins will see much more
	 */
	public static Result index() {
		// Call the video list with all the videos
		//return ok(index.render(Video.find.all()));
		/*Video vid = Ebean.find(Video.class).where().eq("id", 12L).findUnique();
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
			vid.setYear(1999L);
			vid.setState(StateType.OK);
			vid.setMinimumAge(16L);
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
			vid.setYear(1997L);
			vid.setRentalDate(new Date());
			vid.setMinimumAge(10L);
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
			vid.setYear(1989L);
			vid.setMinimumAge(8L);
			
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
			vid.setYear(1908L);
			vid.setRentalDate(new Date());
			Ebean.save(vid);

			vid = new Video();
			vid.setId(16L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Frasier");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(1L);
			vid.setRentalDate(new Date());
			vid.setYear(2001L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(18L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Rome");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentalDate(new Date());
			vid.setYear(2010L);
			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			vid.setMinimumAge(16L);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(19L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("24");
			vid.setCreationDate(new Date());
			vid.setYear(2014L);
			vid.setUpdateDate(new Date());
//			vid.setRentedTo(1L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(20L);
			vid.setContentType(Video.ContentType.TV);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("Famille moderne");
			vid.setOriginalTitle("Modern Family");
			vid.setYear(2006L);
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentalDate(new Date(1371737656));
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
			vid.setRentalDate(new Date(1403273656));
			vid.setYear(2000L);
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
			vid.setRentalDate(new Date(1403273656));
			vid.setYear(1950L);
			vid.setState(StateType.BROKEN);
			Ebean.save(vid);
			
			vid = new Video();
			vid.setId(23L);
			vid.setContentType(Video.ContentType.MOVIE);
			vid.setSupportType(Video.SupportType.DVD);
			vid.setInputTitle("12 monkeys");
			vid.setCreationDate(new Date());
			vid.setUpdateDate(new Date());
			vid.setRentedTo(null);
			vid.setYear(1997L);
			vid.setRentalDate(null);
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
			u.setPassword(u.getFirstName().toLowerCase());
			Ebean.save(u);
			u = new User();
			u.setId(2L);
			u.setName("Bellardie");
			u.setFirstName("Nicolas");
			u.setAdmin(true);
			u.setCreationDate(new Date());
			u.setUpdateDate(new Date());
			u.setEmail(u.getFirstName() + "." + u.getName() + "@orange.com");
			u.setPassword(u.getFirstName().toLowerCase());
			Ebean.save(u);
			
			u = new User();
			u.setId(10L);
			u.setName("Deslaugiers");
			u.setFirstName("Marina");
			u.setAdmin(false);
			u.setCreationDate(new Date());
			u.setUpdateDate(new Date());
			u.setEmail(u.getFirstName() + "." + u.getName() + "@orange.com");
			u.setPassword(u.getFirstName().toLowerCase());
			Ebean.save(u);
			
		}*/
		// we should use the method request().username() but we're not sure it works without the @Security pragma
		return ok(videoclub.render(
						Video.find.findList().size(), 
						User.getByEmail(session("email"))
				)
		);
	}
	/**
	 * This is the video edition method
	 * @param id the id of the video to edit. 0 in case of a video creation
	 * @return the page to the video edition form
	 * Note: one must be authentified to access this page
	 */
	@Security.Authenticated(Secured.class)
	public static Result editVideo(Long id) {
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
			// We implement manually the id increment. It should be a self increment but since we need to display it before actually writing it in the database, we have to do it
			// It could fail if two admins are creating a user at the exact same time, but the chances are low
			String sql = "select max(id) from video";
			SqlRow bug = Ebean.createSqlQuery(sql).findUnique();
			String maxId = bug.getString("max(id)");
			v.setId(Long.decode(maxId) + 1L);
		}
		Form<Video> videoForm = Form.form(Video.class);
		v.setUpdateDate(new Date());

		videoForm = videoForm.fill(v);
		return ok(videoedit.render(videoForm, v.getRentedToUser(), User.getByEmail(request().username())));
	}

	
	/**
	 * The validation page for a video
	 * @return if the validation succeeded, the video list page. If not, the same form with the errors to correct
	 * Note: one must be authenticated to access this page
	 */
	@Security.Authenticated(Secured.class)
	public static Result validateVideo() {
		Form<Video> videoForm = Form.form(Video.class).bindFromRequest();
		if(videoForm.hasErrors()) {
			Video v = Ebean.find(Video.class, Long.parseLong(videoForm.field("id").value()));
			return badRequest(videoedit.render(videoForm, v.getRentedToUser(), User.getByEmail(request().username())));
		} else {
			if ((Ebean.find(Video.class, videoForm.get().getId())) != null) {
				Ebean.update(videoForm.get());
			} else {
				Ebean.save(videoForm.get());
			}
			return redirect("/");
		}
	}
	/**
	 * The method called when admins want to check out videos
	 * @return the checkout web page
	 * Note: one must be authenticated to access this page
	 */
	@SuppressWarnings("unchecked")
	@Security.Authenticated(Secured.class)
	public static Result checkout() {
		return ok(checkout.render(User.find.findList(), User.getByEmail(request().username())));
	}
	
	/**
	 * The method called to validate the checkout
	 * @param userId the user who checks the videos out
	 * @param list the list of videos to check out, in the form of a comma separated String of id
	 * @return the video list page. This could change since there is no particular reason
	 * 
	 */
	@Security.Authenticated(Secured.class)
	public static Result doCheckout(Long userId, String list) {
		String[] parts = list.split(",");
		for (int i = 0; i < parts.length; ++i) {
			Video v = Ebean.find(Video.class, Long.parseLong(parts[i]));
			if (v != null) {
				v.setRentedTo(userId);
				v.setRentalDate(new Date());
				Logger.info("Checkout of video " + v.getId() + " by user " + userId);
				Ebean.save(v);
			} else {
				// TODO: redirect to a page where the error is visible
				Logger.warn("Checkout could not be completed because the video " + Long.parseLong(parts[i]) +  "was not found");
			}
		}
		return redirect("/");
	}
	/**
	 * The method called when admins want to check out videos
	 * @return the checkout web page
	 * Note: one must be authenticated to access this page
	 */
	@SuppressWarnings("unchecked")
	@Security.Authenticated(Secured.class)
	public static Result checkin() {
		return ok(checkin.render(User.find.findList(), User.getByEmail(request().username())));
	}
	
	
	/**
	 * The method called to validate the checkin
	 * @param list the list of videos to check in, in the form of a comma separated String of id
	 * @return the video list page. This could change since there is no particular reason
	 */
	@Security.Authenticated(Secured.class)
	public static Result doCheckin(String list) {
		String[] parts = list.split(",");
		for (int i = 0; i < parts.length; ++i) {
			Video v = Ebean.find(Video.class, Long.parseLong(parts[i]));
			if (v != null) {
				Logger.info("Checkin of video " + v.getId() + " by user " + v.getRentedTo());
				v.setRentedTo(null);
				v.setRentalDate(null);
				Ebean.save(v);
			} else {
				// TODO: redirect to a page where the error is visible
				Logger.warn("Checkin could not be completed because the video " + Long.parseLong(parts[i]) +  "was not found");
			}
		}
		return redirect("/");
	}
	
	private static String countries(List<ProductionCountry> l) {
		String s = "";
		for (ProductionCountry c: l) {
			if (s.length() != 0) {
				s+=", ";
			}
			s += c.getName();
		}
		return s;
	}
	private static String genres(List<Genre> l) {
		String s = "";
		for (Genre g: l) {
			if (s.length() != 0) {
				s+=", ";
			}
			s += g.getName();
		}
		return s;
	}
	private static String createdBy(List<IdNamePair> l) {
		String s = "";
		for (IdNamePair i: l) {
			if (s.length() != 0) {
				s+=", ";
			}
			s += i.getName();
		}
		return s;
	}
	public static Result autoPopulate() {
		List<Video> list = Ebean.find(Video.class).findList();
		for (Video v: list) {
			if (v.getMovieId() != null) {
				Logger.warn("Already populated for: " + v.getId() + "-" + v.getInputTitle());
				continue;
			}
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			BasicVideoInfoSearch search = TmdbApi.searchByTitle(v.getInputTitle(), v.getContentType() == Video.ContentType.TV?"TV":"MOVIE");
			if ((search == null) || (search.getResults().size() == 0)) {
				// Log
				Logger.warn("Could not find basic info for: " + v.getId() + "-" + v.getInputTitle());
				continue;
			}
			
			v.setMovieId(""+search.getResults().get(0).getId());
			if (v.getContentType() == Video.ContentType.MOVIE) {
				MovieInfo info = (MovieInfo) TmdbApi.searchById(v.getMovieId(), v.getContentType() == Video.ContentType.TV?"TV":"MOVIE");
				if (info == null) {
					Logger.warn("Could not find movie info for: " + v.getId() + "-" + v.getInputTitle());
					continue;
				}
				v.setActors(info.getActors());
				v.setDirectors(info.getDirectors());
				v.setBackdropPath(info.getBackdrop_path());
				v.setCountries(countries(info.getProduction_countries()));
				v.setGenres(genres(info.getGenres()));
				v.setOriginalTitle(info.getOriginal_title());
				v.setPosterPath(info.getPoster_path());
				v.setRating(info.getVote_average());
				v.setRuntime(info.getRuntime());
				v.setSummary(info.getOverview());
				v.setTagline(info.getTagline());
			} else {
				TVInfo info = (TVInfo) TmdbApi.searchById(v.getMovieId(), v.getContentType() == Video.ContentType.TV?"TV":"MOVIE");
				if (info == null) {
					Logger.warn("Could not find tv info for: " + v.getId() + "-" + v.getInputTitle());
					continue;
				}
				v.setActors(info.getActors());
				v.setDirectors(createdBy(info.getCreated_by()));
				v.setBackdropPath(info.getBackdrop_path());
				v.setCountries(info.getOrigin_country().get(0));
				v.setGenres(genres(info.getGenres()));
				v.setOriginalTitle(info.getOriginal_name());
				v.setPosterPath(info.getPoster_path());
				v.setRating(info.getVote_average());
				v.setRuntime(info.getEpisode_run_time().get(0));
				v.setSummary(info.getOverview());
				v.setTagline(info.getTagline());
			}
			Logger.warn("Saving: " + v.getId() + "-" + v.getInputTitle());
			Ebean.update(v);
		}
		return ok("ok");
	}
	
	
	// ****************** AJAX CALLS ************************
	/**
	 * Method that answers the Ajax call for rentals by a user, or by the user who has checked the video videoId out 
	 * @param userId
	 * @param videoId
	 * @return
	 */
	public static Result getUserRentals(Long userId, Long videoId) {

		List<Rental> rentals = null;
		// If the user id is given, then we search by user id
		if (userId != 0) {
			rentals = Rental.getRentalsByUserId(userId);
		// else we search by video id
		} else if (videoId != 0) {
			Video v = Ebean.find(Video.class, videoId);
			if (v != null)
				// Get the user who rented that video
				userId = v.getRentedTo();
			else userId = null;
			if (userId != null) {
				rentals = Rental.getRentalsByUserId(userId);
			}
		}
		//Logger.debug("Requested rentals: " + Json.toJson(rentals));
		return ok(Json.toJson(rentals));
	}
	
	/**
	 * @author nicolas
	 * This internal working class is used to get more than the bare video list from the ajax call in getVideoList
	 */
	public static class JSonVideoList {
		public JSonVideoList() {
			
		}
		public int pages;
		public List<Video> list;
	}

	/**
	 * This method returns a list of videos consequently to an ajax call. This method implements a lot of filters so the users may see only certain videos
	 * @param pageNumber the number of the page to return
	 * @param pageSize the maximum number of items to return 
	 * @param old indicator as to whether old videos should be included (by opposition to new only). Old is a matter of internal policy
	 * @param dvd indicator as to whether DVDs should be included. Either DVDs or Blu Rays will be returned
	 * @param br indicator as to whether Blu-rays should be included. Either DVDs or Blu Rays will be returned 
	 * @param pg indicator as to whether videos not suitable for children should be included. Not suitable for children is a matter of internal policy
	 * @param available indicator as to whether only videos that have not been checked out should be included 
	 * @param nameFilter only videos who contain the string in this parameter will be returned. They may contain it either in the title or in the original title
	 * @return the list of videos in json format 
	 */
	public static Result getVideoList(Integer pageNumber, Integer pageSize, Boolean old, Boolean dvd, Boolean br, Boolean pg, Boolean available, String nameFilter) {
		String queryString = "find video ";
		String conditions = "";
		if (old == false) {
			// Looks back 6 months before. This is our current policy for old 
			conditions += "where creationDate > " + new Date(System.currentTimeMillis() - (365 / 2)* 24 * 3600 * 1000L);
		}
		// It's going to be dvd OR (inclusive) blu-ray. Can't be both false
		if ((br == false) || (dvd == false)) {
			if (conditions == "")
				conditions = "where ";
			else conditions += " and ";
			conditions += "supportType = :support";
			
		}
		// Add a condition for age
		if (pg == false) {
			if (conditions == "")
				conditions += " where ";
			else
				conditions += " and ";
			conditions += "minimumAge < :minAge";
		}
		// Add a filter on the name. Both on input and original title
		if (nameFilter != null) {
			if (conditions == "")
				conditions += " where ";
			else
				conditions += " and ";
			conditions += "lower(inputTitle) like :name or lower(originalTitle) like :name";
		}
		// Filter on availability (only not checked out)
		if (available == false) {
			if (conditions == "")
				conditions += " where ";
			else
				conditions += " and ";
			conditions += "rentedTo is null";
		}
		
		// Populate query with values
		Query<Video> query = Ebean.createQuery(Video.class, queryString + conditions);
		if (br == false) {
			// Query only dvd
			query.setParameter("support", Video.SupportType.DVD);				
		} else {
			// Query only blu-ray
			query.setParameter("support", Video.SupportType.BLURAY);
		}
		if (nameFilter != null)
			query.setParameter("name", "%" + nameFilter.toLowerCase() + "%");
		// Not suitable for children will mean 12 and under. This is our current policy
		query.setParameter("minAge", 12);
	
		JSonVideoList list = new JSonVideoList();
		double rowCount = (double) query.findRowCount();
	
		list.list = query.findPagingList(pageSize).getPage(pageNumber - 1).getList();
		
		list.pages = (int)Math.ceil(rowCount / pageSize);
		return ok(Json.toJson(list));
	}
	
	
	
	/**
	 * This method will answer a list of videos matching a given title. The match is based on a "contains" policy 
	 * @param title
	 * @return A list of videos matching a given title in Json format
	 */
	public static Result getVideoByTitle(String title) {
		List<Video> videoList = Ebean.find(Video.class).where().ilike("inputTitle", "%" + title + "%").findList();
		return ok(Json.toJson(videoList));
	}
	/**
	 * This method will answer a video matching a given id
	 * @param id
	 * @return A Json formatted video
	 */
	public static Result getVideoById(Long id) {
		return ok(Json.toJson(Ebean.find(Video.class, id)));
	}
	
	
	/**
	 * Looks for videos either by title or by Id. The method decides itself whether it's a title, an id, or both (think "12 monkeys")
	 * @param titleOrId
	 * @return a Json formatted list of videos matching the parameter 
	 */
	public static Result getVideoByTitleOrId(String titleOrId) {
		Video v = null;
		try {
			v = Ebean.find(Video.class, Long.parseLong(titleOrId));
		} catch (Exception e) {
			// not a number but we don't care
		}
		List<Video> videoList = null;
		if (v != null) {
			videoList = new ArrayList<Video>();
			videoList.add(v);
			videoList.addAll(Ebean.find(Video.class).where().ilike("inputTitle", "%" + titleOrId + "%").findList());
		} else {
			videoList = Ebean.find(Video.class).where().ilike("inputTitle", "%" + titleOrId + "%").findList();
		}
		return ok(Json.toJson(videoList));
	}

	/**
	 * Returns a list of titles from TMDB matching the title in parameter 
	 * @param title title to match
	 * @param type TV or MOVIE
	 * @return the list of titles in Json format
	 */
	public static Result getTMDBTitles(String title, String type) {
		BasicVideoInfoSearch search = TmdbApi.searchByTitle(title, type);
		return ok(Json.toJson(search));
	}	
	/**
	 * Returns the information on a particular video after reading it from TMDB
	 * @param id the TMDB id of the video to look for
	 * @param type TV or MOVIE
	 * @return the information in Json format
	 */
	public static Result getTMDBId(String id, String type) {
		
		VideoInfo info = TmdbApi.searchById(id, type);
		Logger.debug("Returning JSON: " + Json.toJson(info));
		return ok(Json.toJson(info));
	}
	// *********** END AJAX CALLS ****************
}
