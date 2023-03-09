package movie.model;

public class Movies {
	protected String movieId;
	protected String title;
	protected Boolean isAdult;
	protected String year;
	protected int runtimeMinutes;
	protected movieGenre genre;

	public enum movieGenre {
		Romance, Documentary, Action, Drama, Adventure, Biography, War, Comedy, History, Crime, Western, Horror,
		Mystery, Fantasy, Animation, Thriller, Family, Music, SciFi, Sport, Musical, FilmNoir, Adult, Short, News,
		RealityTV, TalkShow, GameShow
	}

	// used for both read from database and write to database
	public Movies(String movieId, String title, Boolean isAdult, String year, int runtimeMinutes, movieGenre genre) {
		this.movieId = movieId;
		this.title = title;
		this.isAdult = isAdult;
		this.year = year;
		this.runtimeMinutes = runtimeMinutes;
		this.genre = genre;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(Boolean isAdult) {
		this.isAdult = isAdult;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public void setRuntimeMinutes(int runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public movieGenre getGenre() {
		return genre;
	}

	public void setGenre(movieGenre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", title=" + title + ", isAdult=" + isAdult + ", year=" + year
				+ ", runtimeMinutes=" + runtimeMinutes + ", genre=" + genre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((isAdult == null) ? 0 : isAdult.hashCode());
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + runtimeMinutes;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movies other = (Movies) obj;
		if (genre != other.genre)
			return false;
		if (isAdult == null) {
			if (other.isAdult != null)
				return false;
		} else if (!isAdult.equals(other.isAdult))
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (runtimeMinutes != other.runtimeMinutes)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
