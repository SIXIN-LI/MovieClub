package movie.model;

import java.util.Objects;

public class Rating {
    protected Integer ratingId;
    protected Movies movie;
    protected Double averageRating;
    protected Integer numOfVotes;

    public Rating(Integer ratingId, Movies movie, Double averageRating, Integer numOfVotes) {
        this.ratingId = ratingId;
        this.movie = movie;
        this.averageRating = averageRating;
        this.numOfVotes = numOfVotes;
    }

    public Rating(Movies movie, Double averageRating, Integer numOfVotes) {
        this.movie = movie;
        this.averageRating = averageRating;
        this.numOfVotes = numOfVotes;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(Integer numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating = (Rating) o;
        return Objects.equals(getRatingId(), rating.getRatingId()) && Objects.equals(getMovie(), rating.getMovie()) && Objects.equals(getAverageRating(), rating.getAverageRating()) && Objects.equals(getNumOfVotes(), rating.getNumOfVotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRatingId(), getMovie(), getAverageRating(), getNumOfVotes());
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", movie=" + movie +
                ", averageRating=" + averageRating +
                ", numOfVotes=" + numOfVotes +
                '}';
    }
}
