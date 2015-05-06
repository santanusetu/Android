package com.santanu.beans;

import java.util.List;

public class Movies {

	private String id;
	private String title;
	private String year;
	private String mpaa_rating;
	private String runtime;
	private String synopsis;

	private ReleasingDate release_dates;
	private Ratings ratings;
	private Poster posters;
	
	private List<AbridgedCast> abridged_cast;
	
	/**
	 * @return the abridged_cast
	 */
	public List<AbridgedCast> getAbridged_cast() {
		return abridged_cast;
	}

	/**
	 * @param abridged_cast
	 *            the abridged_cast to set
	 */
	public void setAbridged_cast(List<AbridgedCast> abridged_cast) {
		this.abridged_cast = abridged_cast;
	}

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis
	 *            the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @return the release_dates
	 */
	public ReleasingDate getRelease_dates() {
		return release_dates;
	}

	/**
	 * @param release_dates
	 *            the release_dates to set
	 */
	public void setRelease_dates(ReleasingDate release_dates) {
		this.release_dates = release_dates;
	}

	/**
	 * @return the ratings
	 */
	public Ratings getRatings() {
		return ratings;
	}

	/**
	 * @param ratings
	 *            the ratings to set
	 */
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}

	/**
	 * @return the posters
	 */
	public Poster getPosters() {
		return posters;
	}

	/**
	 * @param posters
	 *            the posters to set
	 */
	public void setPosters(Poster posters) {
		this.posters = posters;
	}

	
	/**
	 * @return the mpaa_rating
	 */
	public String getMpaa_rating() {
		return mpaa_rating;
	}

	/**
	 * @param mpaa_rating
	 *            the mpaa_rating to set
	 */
	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}

	/**
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * @param runtime
	 *            the runtime to set
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
