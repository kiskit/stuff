package controllers;

import play.mvc.*;
import play.mvc.Http.*;


/**
 * @author nicolas
 *
 */
public class Secured extends Security.Authenticator {

    /* (non-Javadoc)
     * @see play.mvc.Security.Authenticator#getUsername(play.mvc.Http.Context)
     */
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    /* (non-Javadoc)
     * @see play.mvc.Security.Authenticator#onUnauthorized(play.mvc.Http.Context)
     */
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
}