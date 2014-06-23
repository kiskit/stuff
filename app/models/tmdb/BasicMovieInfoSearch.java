package models.tmdb;

import java.util.List;

public class BasicMovieInfoSearch {
	private int page;
	private List <BasicMovieInfo> results;
	private int total_pages;
	private int total_results;

	BasicMovieInfoSearch () {

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<BasicMovieInfo> getResults() {
		return results;
	}
	public void setResults(List<BasicMovieInfo> results) {
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

	@Override
	public String toString() {
		return "BasicMovieInfoSearch [page=" + page + ", results="
				+ results + ", total_pages=" + total_pages
				+ ", total_results=" + total_results + "]";
	}



}


