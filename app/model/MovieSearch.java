package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieSearch {
	MovieSearch() {

	}



	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Movie> getResults() {
		return results;
	}
	public void setResults(List<Movie> results) {
		this.results = results;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

	public static List<Movie> populateFromRemoteDB(String key, String title) {

		List<Movie> movies = null;
		MovieSearch search = null;
		String url = "https://api.themoviedb.org/3/search/movie?api_key="+key+"&query=" + title; 
		URL obj;
		try {
			obj = new URL(url);

			System.out.println("URL:" + url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response);
			in.close();
			System.out.println("Mapping objects");			
			ObjectMapper objectMapper = new ObjectMapper();
			//		movies = objectMapper.readValue(response.toString(), new TypeReference<List<Movie>>(){});
			//movies = new ArrayList<Movie>();
			search = objectMapper.readValue(response.toString(), MovieSearch.class);
			if (search != null) {
				movies = search.getResults();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Dang, an exception");
			e.printStackTrace();
		}
		return (movies);
	}

	int page;
	List <Movie> results;
	int total_pages;
	int total_results;
}
