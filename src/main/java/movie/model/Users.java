package movie.model;

import java.util.Objects;

public class Users {
    protected int userId; // auto-increment id
    protected String firstName;
    protected String lastName;
    protected String password;
    protected genderType gender;

    public enum genderType {
        FEMALE, MALE
    }

    // used for read from database
    public Users(int userId, String firstName, String lastName, genderType gender) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    // used for write to database while id will be set when created in dao
    public Users(String firstName, String lastName, genderType gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public genderType getGender() {
        return gender;
    }

    public void setGender(genderType gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
            "userId=" + userId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", password='" + password + '\'' +
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
        return userId == users.userId && firstName.equals(users.firstName) && lastName.equals(
            users.lastName) && password.equals(users.password) && gender == users.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, password, gender);
    }
}
