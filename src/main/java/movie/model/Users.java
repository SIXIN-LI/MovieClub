package movie.model;

import java.util.Objects;

public class Users {
    protected int user_id; // auto-increment id
    protected String first_name;
    protected String last_name;
    protected genderType gender;

    public enum genderType {
        FEMALE, MALE
    }

    // used for read from database
    public Users(int user_id, String first_name, String last_name, genderType gender) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    // used for write to database while id will be set when created in dao
    public Users(String first_name, String last_name, genderType gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public genderType getGender() {
        return gender;
    }

    public void setGender(genderType gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Users{" +
            "user_id=" + user_id +
            ", first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            ", gender=" + gender +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        return user_id == users.user_id && first_name.equals(users.first_name) && last_name.equals(
            users.last_name) && gender == users.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, first_name, last_name, gender);
    }
}
