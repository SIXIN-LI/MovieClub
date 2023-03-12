package movie.model;

import java.util.Objects;

public class KnownMovies {
    private int knownMovieId; // auto-increment id
    private Crew crew;
    private Movies movie;

    public KnownMovies(int knownMovieId, Crew crew, Movies movie) {
        this.knownMovieId = knownMovieId;
        this.crew = crew;
        this.movie = movie;
    }

    public KnownMovies(Crew crew, Movies movie) {
        this.crew = crew;
        this.movie = movie;
    }

    public int getKnownMovieId() {
        return knownMovieId;
    }

    public void setKnownMovieId(int knownMovieId) {
        this.knownMovieId = knownMovieId;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KnownMovies that = (KnownMovies) o;
        return knownMovieId == that.knownMovieId && crew.equals(that.crew) && movie.equals(
            that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knownMovieId, crew, movie);
    }

    @Override
    public String toString() {
        return "KnownMovies{" +
            "knownMovieId=" + knownMovieId +
            ", crew=" + crew +
            ", movie=" + movie +
            '}';
    }
}
