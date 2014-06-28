package controllers;

import models.User;
import play.Logger;
import play.mvc.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import play.data.Form;
import views.html.*;

import java.util.Date;

/**
 * @author nicolas
 * The controller for everything related to users (users list, user edition)
 */
public class Users extends Controller {

	/**
	 * @return the web page for the users list
	 * Note: one must be authentified to access this page
	 */
	@SuppressWarnings("unchecked")
	@Security.Authenticated(Secured.class)
	public static Result index() {    	
		return ok(userlist.render(User.find.findList(), User.getByEmail(request().username())));
	}
	
	/**
	 * @param id
	 * @return the web page for editing the details of a given user
	 * Note: one must be authentified to access this page
	 */
	@Security.Authenticated(Secured.class)
	public static Result editUser(Long id) {
		/**
		 * If the User already exists, we can look for it in the database
		 * We will populate all the fields with it
		 * If it does't exist, we are creating a new one
		 * Note that dates are fixed by the moment the operations are taking place (creation or update)
		 */
		User u = Ebean.find(User.class, id);
		if (u == null) {
			// User not found, creating
			u = new User();
			u.setCreationDate(new Date());
			// The id should be an auto increment, but we need to display it, so we are calculating it beforehand. This could make us fail at insert time, but there are little chances two admins are creating users at the same time
			String sql = "select max(id) from User";
			SqlRow bug = Ebean.createSqlQuery(sql).findUnique();
			String maxId = bug.getString("max(id)");
			if (maxId != null) {
				u.setId(Long.decode(maxId) + 1L);
			} else {
				u.setId(1L);
			}
		}
		Form<User> userForm = Form.form(User.class);
		u.setUpdateDate(new Date());
		// pre-fills the form with user data
		userForm = userForm.fill(u);
		Logger.debug(request().username() + " requested the edition page of user " + u.getId() + (u.getName() == null?" (new user)":" (" + u.getFullName() + ")"));
		return ok(useredit.render(userForm, User.getByEmail(request().username())));
	}
	/**
	 * The method is called when the admin has finished editing the user. It will record the changes
	 * @return the web page to the users list if the validation went right. If it went wrong, we stay on the user edition page
	 */
	@Security.Authenticated(Secured.class)
	public static Result validateUser() {
		Form<User> userForm = Form.form(User.class).bindFromRequest();
		if(userForm.hasErrors()) {
			return badRequest(useredit.render(userForm, User.getByEmail(request().username())));
		} else {
			// If user exists, update
			if (Ebean.find(User.class, userForm.get().getId()) != null) {
				Logger.info("Updating user " + userForm.get().getId() + " (" + userForm.get().getFullName() + ")" );
				Ebean.update(userForm.get());
			// If it doesn't exist, insert
			} else {
				Logger.info("Creating new user " + userForm.get().getId() + " (" + userForm.get().getFullName() + ")" );
				Ebean.save(userForm.get());
			}
			return redirect("/users");
		}
	}
}
