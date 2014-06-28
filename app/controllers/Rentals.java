package controllers;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import models.Rental;
import models.User;
import models.Video;
import play.Configuration;
import play.Logger;
import play.mvc.*;
import views.html.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.avaje.ebean.Ebean;

/**
 * The Rentals class is a utility class used mainly for displaying the late rentals and for the AJAX call that will email users who are late with giving back videos
 * @author nicolas
 *
 */
public class Rentals extends Controller {

	/**
	 * Web page for the late users
	 * @return the web page for the list of users who have checked out videos
	 */
	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(
				laterentals.render(
						Rental.getAllRentals(), 
						User.getByEmail(request().username())
				)
		);
	}
	
	/**
	 * @param userId the user to whom the email shall be sent
	 * @param videoId the video about which the user should be reminded
	 * @return a message indicating whether the email could be sent
	 * For now this method returns ok. It should probably return some server error instead, but that was easier to catch in the returning ajax
	 */
	public static Result sendEmail(Long userId, Long videoId) {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", Configuration.root().getString("videoclub.mailserver"));
		props.put("mail.smtp.port", "587");
		Logger.debug("Using this smtp " + Configuration.root().getString("videoclub.mailserver"));
		
        Session session = Session.getDefaultInstance(props, null);
        Video v = Ebean.find(Video.class, videoId);
        if (v == null) {
        	Logger.warn("Video " + videoId + " was not found in the database");
        	return ok("Vidéo non trouvée. Contactez l'admin du site svp");
        }
        User u = Ebean.find(User.class, userId);
        if (u == null) {
        	Logger.warn("User " + userId + " was not found in the database");
        	return ok("Abonné non trouvé. Contactez l'admin du site svp");	
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(v.getRentalDate());
        String msgBody = "Bonjour M. " + u.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        msgBody += "Selon notre système, vous avez en votre possession la vidéo " + v.getInputTitle() + " depuis le " + sdf.format(v.getRentalDate()) + ".";
        msgBody += "\nMerci de bien vouloir faire le nécessaire pour contacter le vidéo club et de restituer la vidéo concernée au plus vite.";
        msgBody += "\nCordialement";
        msgBody += "\nVotre vidéo club.";

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(Configuration.root().getString("videoclub.adminmailinglist"), "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(u.getEmail(), "Mr. User"));
            // Mailing list for the admins of the VC
            msg.addRecipient(Message.RecipientType.BCC,
                    new InternetAddress(Configuration.root().getString("videoclub.adminmailinglist"), "Mr. Admin"));
            msg.setSubject("Vous avez des vidéos en retard");
            msg.setText(msgBody);
            Transport.send(msg); 

        } catch (AddressException e) {
        	Logger.warn("Failed to send email. Reason: " + e.toString());
        	return ok("Problème d'adresse. Contactez l'admin du site svp");
        } catch (MessagingException e) {
        	Logger.warn("Failed to send email. Reason: " + e.toString());
        	return ok("Problème avec le message. Contactez l'admin du site svp");
        } catch (UnsupportedEncodingException e) {
        	Logger.warn("Failed to send email. Reason: " + e.toString());
        	return ok("Problème d'encodage. Contactez l'admin du site svp");
		} catch (Exception e) {
			Logger.warn("Failed to send email. Reason: " + e.toString());
			return ok("Problème avec le mail. Contactez l'admin du site svp");
		}
		return ok("");
	}

}
