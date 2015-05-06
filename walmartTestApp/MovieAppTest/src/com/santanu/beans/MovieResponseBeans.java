package com.santanu.beans;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieResponseBeans implements Parcelable {

	private String total;
	private List<Movies> movies;
	private Links links;
	private String link_template;

	
	public MovieResponseBeans() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the movies
	 */
	public List<Movies> getMovies() {
		return movies;
	}

	/**
	 * @param movies
	 *            the movies to set
	 */
	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

	/**
	 * @return the links
	 */
	public Links getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(Links links) {
		this.links = links;
	}

	/**
	 * @return the link_template
	 */
	public String getLink_template() {
		return link_template;
	}

	/**
	 * @param link_template
	 *            the link_template to set
	 */
	public void setLink_template(String link_template) {
		this.link_template = link_template;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(total);
		dest.writeList(movies);
		dest.writeValue(links);
		dest.writeString(link_template);

	}

}
