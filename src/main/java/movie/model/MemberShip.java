package movie.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MemberShip {
    protected String membershipId;
    protected User user;
    protected LocalDateTime timestamp;

    public MemberShip(String membershipId, User user, LocalDateTime timestamp) {
        this.membershipId = membershipId;
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberShip)) return false;
        MemberShip that = (MemberShip) o;
        return Objects.equals(getMembershipId(), that.getMembershipId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMembershipId(), getUser(), getTimestamp());
    }

    @Override
    public String toString() {
        return "MemberShip{" +
                "membershipId='" + membershipId + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }
}

