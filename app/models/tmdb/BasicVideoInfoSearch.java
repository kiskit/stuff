package models.tmdb;

import java.util.List;

/**
 * @author nicolas
 * A wrapper for search results with additional information
 */
public class BasicVideoInfoSearch {
	/**
	 * the current page for the search
	 */
	private int page;
	/**
	 * the list of results
	 */
	private List <BasicVideoInfo> results;
	/**
	 * the number of pages of results
	 */
	private int total_pages;
	/**
	 * the total number of results
	 */
	private int total_results;

	/**
	 * Default constructor
	 */
	BasicVideoInfoSearch () {

	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the results
	 */
	public List<BasicVideoInfo> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(List<BasicVideoInfo> results) {
		this.results = results;
	}

	/**
	 * @return the total_pages
	 */
	public int getTotal_pages() {
		return total_pages;
	}

	/**
	 * @param total_pages the total_pages to set
	 */
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	/**
	 * @return the total_results
	 */
	public int getTotal_results() {
		return total_results;
	}

	/**
	 * @param total_results the total_results to set
	 */
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicVideoInfoSearch [page=" + page + ", results=" + results
				+ ", total_pages=" + total_pages + ", total_results="
				+ total_results + "]";
	}


}


