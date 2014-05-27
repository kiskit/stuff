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
import java.util.Date;

public class Users extends Controller {
	

    public static Result index() {    	
        return ok(index.render("Your new application is ready."));
    }
}
