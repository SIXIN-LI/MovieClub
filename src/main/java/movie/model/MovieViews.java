package movie.model;

import java.util.Date;

public class MovieViews{
	
	protected int  viewId;
	protected Users userId;
	protected Movies movieId;
	protected Date viewTime;
	
	public MovieViews(int viewId, Users userId, Movies movieId, Date viewTime) {

		this.viewId = viewId;
		this.userId = userId;
		this.movieId = movieId;
		this.viewTime = viewTime;
	}

	public MovieViews(int viewId) {

		this.viewId = viewId;
	}

	public MovieViews(Users userId, Movies movieId, Date viewTime) {
		super();
		this.userId = userId;
		this.movieId = movieId;
		this.viewTime = viewTime;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Movies getMovieId() {
		return movieId;
	}

	public void setMovieId(Movies movieId) {
		this.movieId = movieId;
	}

	public Date getViewTime() {
		return viewTime;
	}

	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}
	
	
	
	
	
}