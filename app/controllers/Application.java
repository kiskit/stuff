package controllers;

import java.util.ArrayList;
import java.util.List;

import play.*;
import play.mvc.*;

import views.html.*;
import model.*;
public class Application extends Controller {

    public static Result index() {
    	//ArrayList<Movie> movieList = new ArrayList<Movie>();
    	ArrayList<String> titles = new ArrayList<String>();
    	titles.add("Dragons");
    	titles.add("Cloverfield");
    	titles.add("Titanic");
    	titles.add("Godzilla");
    	titles.add("Eragon");
    	titles.add("Spider");
    	List<Movie> movieList = new ArrayList<Movie>();
    	List<Movie> tMovieList = null;
    	for (int i = 0; i < titles.size(); ++i) {
    		tMovieList = MovieSearch.populateFromRemoteDB(key, titles.get(i));
    		if ((tMovieList != null) && (tMovieList.size() > 0))
    			movieList.add(tMovieList.get(0));
    		else
    			System.out.println("Could not find movie: " + titles.get(i));
    	}
    	
        return ok(mlist.render(movieList));
    }
    public static Result admin() {
    	// TODO test session
    	return ok(admin.render());
    }
    
    public static Result userlist(String filter) {
    	// TODO test session
    	return ok(userlist.render(filter));
    }
    
    public static Result useredit(String id) {
    	// TODO test session
    	return ok(useredit.render(id));
    }
    
    public static Result movieedit(String id) {
    	// TODO test session
    	return ok(movieedit.render(id));
    }
    /*
    public static Result sessionWarning() {
    	return ok()
    }*/
    
    
    public static String key = "c589965ca14962d100212f66a6a2b1c5";
    
    
    
    
    
/*
 * package controllers;

import models.Project;
import models.Task;
import models.User;
import play.*;
import play.data.*;
import play.mvc.*;


import views.html.*;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(index.render(
                Project.findInvolving(request().username()),
                Task.findTodoInvolving(request().username()),
                User.find.byId(request().username())
        ));
    }

    public static Result login(){
        return ok(
                login.render(Form.form(Login.class))
        );
    }

    public static Result authenticate(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            return badRequest(login.render(loginForm));
        }
        else{
            session().clear();
            session("email", loginForm.get().email);
            return redirect(controllers.routes.Application.index());
        }
    }


    public static class Login{
        public String email;
        public String password;

        public String validate(){
            if (User.authenticate(email, password) == null){
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result logout(){
        session().clear();
        flash("success", "You've been logged out");
        return redirect(controllers.routes.Application.login());
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        controllers.routes.javascript.Projects.add(),
                        controllers.routes.javascript.Projects.delete(),
                        controllers.routes.javascript.Projects.rename(),
                        controllers.routes.javascript.Projects.addGroup()
                )
        );
    }

}    
 */
    
    
    
    
    
    
}
