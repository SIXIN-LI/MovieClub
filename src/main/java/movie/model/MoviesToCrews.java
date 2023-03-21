package movie.model;

import java.util.Objects;

public class MoviesToCrews {
    protected int movieToCrewId;
    protected Movies movie;
    protected Crew crew;
    protected JobCategory jobCategory;

    public MoviesToCrews(int movieToCrewId, Movies movie, Crew crew, JobCategory jobCategory) {
        this.movieToCrewId = movieToCrewId;
        this.movie = movie;
        this.crew = crew;
        this.jobCategory = jobCategory;
    }

    public MoviesToCrews(Movies movie, Crew crew, JobCategory jobCategory) {
        this.movie = movie;
        this.crew = crew;
        this.jobCategory = jobCategory;
    }

    // Getters and Setters

    public int getMovieToCrewId() {
        return movieToCrewId;
    }

    public Movies getMovie() {
        return movie;
    }

    public Crew getCrew() {
        return crew;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setMovieToCrewId(int movieToCrewId) {
        this.movieToCrewId = movieToCrewId;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    // equals method


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesToCrews that)) return false;
        return getMovieToCrewId() == that.getMovieToCrewId() && getMovie().equals(that.getMovie()) && getCrew().equals(that.getCrew()) && getJobCategory() == that.getJobCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieToCrewId(), getMovie(), getCrew(), getJobCategory());
    }

    @Override
    public String toString() {
        return "MovieToCrews{" +
                "movieToCrewId=" + movieToCrewId +
                ", movie=" + movie +
                ", crew=" + crew +
                ", jobCategory=" + jobCategory +
                '}';
    }

    // JobCategory enum

    public enum JobCategory {
        ACTRESS,
        ACTOR,
        DIRECTOR,
        SELF,
        PRODUCER,
        CINEMATOGRAPHER,
        COMPOSER,
        WRITER,
        PRODUCTION_DESIGNER,
        EDITOR,
        ARCHIVE_FOOTAGE,
        ARCHIVE_SOUND
    }
}

