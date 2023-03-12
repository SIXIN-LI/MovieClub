package main.java.movie.model;

import java.util.Objects;

public class Favorites {
    protected int favoriteId; // auto-increment id
    protected Movies movies;
    protected Users users;


    /**
	 * @param favoriteId
	 * @param movies
	 * @param users
	 */
	public Favorites(int favoriteId, Movies movies, Users users) {
		super();
		this.favoriteId = favoriteId;
		this.movies = movies;
		this.users = users;
	}


	public int getFavoriteId() {
		return favoriteId;
	}


	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}


	public Movies getMovies() {
		return movies;
	}


	public void setMovies(Movies movies) {
		this.movies = movies;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


	@Override
	public int hashCode() {
		return Objects.hash(favoriteId, movies, users);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorites other = (Favorites) obj;
		return favoriteId == other.favoriteId && Objects.equals(movies, other.movies)
				&& Objects.equals(users, other.users);
	}


	@Override
	public String toString() {
		return "Favorites [favoriteId=" + favoriteId + ", movies=" + movies + ", users=" + users + "]";
	}
	
	

	
}
