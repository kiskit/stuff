package models;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;




public class MovieInfo {
	public MovieInfo() {

	}

	public class Collection {
		public Collection () {
			
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPoster_path() {
			return poster_path;
		}
		public void setPoster_path(String poster_path) {
			this.poster_path = poster_path;
		}
		public String getBackdrop_path() {
			return backdrop_path;
		}
		public void setBackdrop_path(String backdrop_path) {
			this.backdrop_path = backdrop_path;
		}


		@Override
		public String toString() {
			return "Collection [id=" + id + ", name=" + name + ", poster_path="
					+ poster_path + ", backdrop_path=" + backdrop_path + "]";
		}

		private String id;
		private String name;
		private String poster_path;
		private String backdrop_path;
	}
	@JsonRootName("genre")
	public class Genre {
		public Genre() {

		}
		public Genre (int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Genre [id=" + id + ", name=" + name + "]";
		}
	    @JsonProperty("id")
		private int id;
	    @JsonProperty("name")
		private String name;
	}
	public class ProductionCountry { 
		public ProductionCountry() {

		}
		public String getIso_3166_1() {
			return iso_3166_1;
		}
		public void setIso_3166_1(String iso_3166_1) {
			this.iso_3166_1 = iso_3166_1;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "ProductionCountry [iso_3166_1=" + iso_3166_1 + ", name="
					+ name + "]";
		}

		private String iso_3166_1;
		private String name;
	}
	public class ProductionCompany{
		public ProductionCompany() {

		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "ProductionCompany [id=" + id + ", name=" + name + "]";
		}

		private int id;
		private String name;
	}
	public class Language {
		public Language() {

		}
		public String getIso_639_1() {
			return iso_639_1;
		}
		public void setIso_639_1(String iso_639_1) {
			this.iso_639_1 = iso_639_1;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "Language [iso_639_1=" + iso_639_1 + ", name=" + name + "]";
		}

		private String iso_639_1;
		private String name;

	}
	
	
	
	
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	
	public Collection getBelongs_to_collection() {
		return belongs_to_collection;
	}
	public void setBelongs_to_collection(Collection belongs_to_collection) {
		this.belongs_to_collection = belongs_to_collection;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public float getPopularity() {
		return popularity;
	}
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public List<ProductionCompany> getProduction_companies() {
		return production_companies;
	}
	public void setProduction_companies(List<ProductionCompany> production_companies) {
		this.production_companies = production_companies;
	}
	public List<ProductionCountry> getProduction_countries() {
		return production_countries;
	}
	public void setProduction_countries(List<ProductionCountry> production_countries) {
		this.production_countries = production_countries;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public List<Language> getSpoken_languages() {
		return spoken_languages;
	}
	public void setSpoken_languages(List<Language> spoken_languages) {
		this.spoken_languages = spoken_languages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getVote_average() {
		return vote_average;
	}
	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	
	
	@Override
	public String toString() {
		return "MovieInfo [adult=" + adult + ", budget=" + budget
				+ ", belongs_to_collection=" + belongs_to_collection
				+ ", genres=" + genres + ", homepage=" + homepage + ", id="
				+ id + ", imdb_id=" + imdb_id + ", original_title="
				+ original_title + ", overview=" + overview + ", popularity="
				+ popularity + ", poster_path=" + poster_path
				+ ", production_companies=" + production_companies
				+ ", production_countries=" + production_countries
				+ ", release_date=" + release_date + ", revenue=" + revenue
				+ ", runtime=" + runtime + ", spoken_languages="
				+ spoken_languages + ", status=" + status + ", tagline="
				+ tagline + ", title=" + title + ", vote_average="
				+ vote_average + ", vote_count=" + vote_count + "]";
	}


	private boolean adult;
	private String backdrop_path;
	private int budget;
	private Collection belongs_to_collection;
	private List<Genre> genres;
	private String homepage;
	private int id;
	private String imdb_id;
	private String original_title;
	private String overview;
	private float popularity;
	private String poster_path;
	private List<ProductionCompany> production_companies;
	private List<ProductionCountry> production_countries; 
	private String release_date;
	private String revenue;
	private int runtime;
	private List<Language> spoken_languages;
	private String status;
	private String tagline;
	private String title;
	private float vote_average;
	private int vote_count;

}