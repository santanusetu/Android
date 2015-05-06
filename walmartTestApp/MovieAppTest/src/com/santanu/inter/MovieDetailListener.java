package com.santanu.inter;

import com.santanu.beans.MovieResponseBeans;

/**
 * 
 * Listener Interface that will be used for 
 * event bus communication across the application 
 *
 */
public interface MovieDetailListener {
	public void onMovieClick(MovieResponseBeans arg0,int position);
}
