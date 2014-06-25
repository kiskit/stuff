package models.tmdb;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TmdbApi {
	private static String key = "c589965ca14962d100212f66a6a2b1c5";

	private static final String baseURL = "http://api.themoviedb.org/3/";
	private static ObjectMapper mapper = new ObjectMapper();


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
			// TODO: do something with it
			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			responseStr = response.toString();
			System.out.println(responseStr);
		} catch (Exception e) {
			throw (new TmdbException(e));
		}

		return responseStr;
	}

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
			response = getContent(url);
			search = mapper.readValue(response, BasicVideoInfoSearch.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (search);
	}
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
				System.out.println("Trying to return tv info");
				info = mapper.readValue(response, TVInfo.class);
			} else {
				System.out.println("Trying to return movie info");
				info = mapper.readValue(response, MovieInfo.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (info);
	}
}
