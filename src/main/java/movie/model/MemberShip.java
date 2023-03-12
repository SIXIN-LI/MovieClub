package movie.model;

import java.sql.Timestamp;
import java.util.Objects;

public class MemberShip {
    protected String membershipId;
    protected Users user;
    protected Timestamp timestamp;

    public MemberShip(String membershipId, Users user, Timestamp timestamp) {
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
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

