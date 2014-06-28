package models.tmdb;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import play.Configuration;
import play.Logger;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nicolas
 * The internal API class to query the TMDB json API
 */
public class TmdbApi {
	
	/**
	 * the TMDB key 
	 */
	private static String key = Configuration.root().getString("videoclub.tmdb.api_key");

	/**
	 * the base for all TMDB URL calls
	 */
	private static final String baseURL = Configuration.root().getString("videoclub.tmdb.baseurl");
			
	/**
	 * the object mapper for Json decoding
	 */
	private static ObjectMapper mapper = new ObjectMapper();


	/**
	 * @param url
	 * @return the content from the API on themoviedb.org as queried by the url parameter
	 * @throws TmdbException
	 */
	public static String getContent(String url) throws TmdbException {
		URL obj;
		String responseStr = null;
		try {
			obj = new URL(url);

			System.out.println("URL:" + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new TmdbException("Got error code from TMDB " + responseCode);
			}
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			responseStr = response.toString();
			Logger.debug("Response from TMDB: " + responseStr);
		} catch (Exception e) {
			throw (new TmdbException(e));
		}

		return responseStr;
	}

	/**
	 * @param title
	 * @param type
	 * @return the basic information extracted from a search by title from themoviedb.org
	 */
	public static BasicVideoInfoSearch searchByTitle(String title, String type) {
		String response = null;
		BasicVideoInfoSearch search = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String url = baseURL + "search/";
		if ("TV".equals(type)) {
			url += "tv";
		} else {
			url += "movie";
		}
		try {
			url += "?api_key="+key+"&query=" + URLEncoder.encode(title, "UTF-8");
			Logger.debug("Querying title info from TMDB " + title);
			response = getContent(url);
			search = mapper.readValue(response, BasicVideoInfoSearch.class);
		} catch (Exception e) {
			Logger.warn("Search by title returned an exception: " + e.toString());
		}
		return (search);
	}
	
	
	/**
	 * Finds the casting for the tv show whose id is given
	 * @return the cast for a tv show
	 */
	private static Credits getTVCredits(String id) {
		String response = null;
		Credits info = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String url = baseURL + "tv/" + id + "/season/1/episode/1/credits";
		url += "?api_key="+key;
		try {
			response = getContent(url);
			info = mapper.readValue(response, Credits.class);
		} catch (Exception e) {
			Logger.warn("Credits search by ID returned an exception: " + e.toString());
		}
		return info;		
	}
	
	
	/**
	 * Finds the casting for the movie whose id is given
	 * @return the cast for a movie
	 */
	private static Credits getMovieCredits(String id) {
		String response = null;
		Credits info = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String url = baseURL + "movie/" + id + "/credits";
		url += "?api_key="+key;
		try {
			response = getContent(url);
			info = mapper.readValue(response, Credits.class);
		} catch (Exception e) {
			Logger.warn("Credits search by ID returned an exception: " + e.toString());
		}
		return info;		
	}
	private static String getDirectors(Credits credits) {
		String list = "";
		for (CastItem c : credits.getCrew()) {
			if ("director".equalsIgnoreCase(c.getJob())) {
				if (!list.isEmpty())
					list+=", ";
				list += c.getName();
			}
		}
		return list;
	}
	/**
	 * @param credits the credits from which we want the actors extracted
	 * @param max the maximum number of actors we want
 	 * @return a comma separated string with the names of actors
	 */
	private static String getActors(Credits credits, int max) {
		String list = "";
		int count = 0;
		for (CastItem c : credits.getCast()) {
			if (count == max) break;
			if (!list.isEmpty())
				list+=", ";
			list += c.getName();
			++count;
		}
		return list;
	}
	
	/**
	 * @param id
	 * @param type
	 * @return the basic information extracted from a search by ID from themoviedb.org
	 */
	public static VideoInfo searchById(String id, String type) {
		String response = null;
		VideoInfo info = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String url = baseURL;
		if ("TV".equals(type)) {
			url += "tv/";
		} else {
			url += "movie/";
		}
		try {
			url += id + "?api_key=" + key+"&language=fr";
			response = getContent(url);
			if ("TV".equals(type)) {
				Logger.debug("Querying TV info from TMDB " + id);
				info = mapper.readValue(response, TVInfo.class);
				Credits credits = getTVCredits(id);
				info.setActors(getActors(credits, 4));
			} else {
				Logger.debug("Querying movie info from TMDB " + id);
				MovieInfo mInfo = mapper.readValue(response, MovieInfo.class);
				// Query additional info for this movie
				Credits credits = getMovieCredits(id);
				mInfo.setDirectors(getDirectors(credits));
				mInfo.setActors(getActors(credits, 4));
				info = mInfo;
			}
		} catch (Exception e) {
			Logger.warn("Search by ID returned an exception: " + e.toString());
		}
		return (info);
	}
}
