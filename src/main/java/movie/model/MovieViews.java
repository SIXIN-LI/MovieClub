package movie.model;

import java.util.Date;

public class MovieViews{
	
	protected int  viewId;
	protected Users user;
	protected Movies movie;
	protected Date viewTime;
	
	

	public MovieViews(int viewId, Users user, Movies movie, Date viewTime) {

		this.viewId = viewId;
		this.user = user;
		this.movie = movie;
		this.viewTime = viewTime;
	}



	public MovieViews(int viewId) {

		this.viewId = viewId;
	}



	public MovieViews(Users user, Movies movie, Date viewTime) {

		this.user = user;
		this.movie = movie;
		this.viewTime = viewTime;
	}



	public int getViewId() {
		return viewId;
	}



	public void setViewId(int viewId) {
		this.viewId = viewId;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public Movies getMovie() {
		return movie;
	}



	public void setMovie(Movies movie) {
		this.movie = movie;
	}



	public Date getViewTime() {
		return viewTime;
	}



	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}

	
	
	
	
	
	
}