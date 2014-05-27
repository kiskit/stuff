package controllers;

import models.User;
import play.mvc.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import play.data.Form;
import play.libs.Yaml;
import views.html.*;

import java.util.Date;

public class Users extends Controller {
	

    public static Result index() {    	
    	return ok(userlist.render(User.find.findList(), new User()));
    }
    
    public static Result editUser(Long id) {
		System.out.println("Editing user " + id);
		/**
		 * If the User already exists, we can look for it in the database
		 * We will populate all the fields with it
		 * If it does't exist, we are creating a new one
		 */

		User u = Ebean.find(User.class, id);
		if (u == null) {
			// User not found, creating
			u = new User();
			u.setCreationDate(new Date());
			String sql = "select max(id) from User";
			SqlRow bug = Ebean.createSqlQuery(sql).findUnique();
			System.out.println("Bug :" + bug.toString());
			String maxId = bug.getString("max(id)");
			if (maxId != null) {
				System.out.println("Max ID found : " + maxId);
				u.setId(Long.decode(maxId) + 1L);
			} else {
				System.out.println("No max ID found. Starting at 1");
				u.setId(1L);
			}
		}
		Form<User> userForm = Form.form(User.class);
		u.setUpdateDate(new Date());

		userForm = userForm.fill(u);
		User admin = new User();
		admin.setEmail("admin@admin.com");
		admin.setFirstName("Bruce");
		admin.setName("Wayne");
		return ok(useredit.render(userForm, admin));
	}
	public static Result validateUser() {
		Form<User> userForm = Form.form(User.class).bindFromRequest();
		if(userForm.hasErrors()) {
			System.out.println("Bad request Validating user");
			User admin = new User();
			admin.setEmail("admin@admin.com");
			admin.setFirstName("Bruce");
			admin.setName("Wayne");
			return badRequest(useredit.render(userForm, admin));
		} else {
			if (Ebean.find(User.class, userForm.get().getId()) != null) {
				System.out.println("Updating user " + userForm.toString());
				Ebean.update(userForm.get());	
			} else {
				System.out.println("Inserting user" + userForm.toString());
				Ebean.save(userForm.get());
			}
			
			return ok(userlist.render(User.find.findList(), new User()));
		}

	}
    
}
