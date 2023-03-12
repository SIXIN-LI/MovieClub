package movie.model;

import java.sql.Timestamp;
import java.util.Objects;


public class Intentions {
    protected int intentionId;
    protected Users user;
    protected Movies movie;
    protected Timestamp timestamp;
    protected Action action;

    public Intentions(int intentionId, Users user, Movies movie, Timestamp timestamp, Action action) {
        this.intentionId = intentionId;
        this.user = user;
        this.movie = movie;
        this.timestamp = timestamp;
        this.action = action;
    }

    // Getters and Setters

    public int getIntentionId() {
        return intentionId;
    }

    public Users getUser() {
        return user;
    }


    public Movies getMovie() {
        return movie;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Action getAction() {
        return action;
    }

    public void setIntentionId(int intentionId) {
        this.intentionId = intentionId;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intentions that)) return false;
        return getIntentionId() == that.getIntentionId() && getUser().equals(that.getUser()) && getMovie().equals(that.getMovie()) && getTimestamp().equals(that.getTimestamp()) && getAction().equals(that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntentionId(), getUser(), getMovie(), getTimestamp(), getAction());
    }

    @Override
    public String toString() {
        return "Intentions{" +
                "intentionId=" + intentionId +
                ", user=" + user +
                ", movie=" + movie +
                ", timestamp=" + timestamp +
                ", action='" + action + '\'' +
                '}';
    }

    // Action enum

    public enum Action {
        WATCHED,
        WATCHING,
        WANT_TO_WATCH
    }
}