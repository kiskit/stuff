package models.tmdb;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import play.Logger;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TmdbApi {
	private static String key = "c589965ca14962d100212f66a6a2b1c5";

	private static final String baseURL = "http://api.themoviedb.org/3/";
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
			} else {
				Logger.debug("Querying movie info from TMDB " + id);
				info = mapper.readValue(response, MovieInfo.class);
			}
		} catch (Exception e) {
			Logger.warn("Search by ID returned an exception: " + e.toString());
		}
		return (info);
	}
}
