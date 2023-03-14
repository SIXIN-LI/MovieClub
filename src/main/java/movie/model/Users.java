package movie.model;

import java.util.Objects;

public class Users {
    protected int userId; // auto-increment id
    protected String userName;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected genderType gender;

    public enum genderType {
        FEMALE, MALE
    }

    // used for read from database
    public Users(int userId, String userName, String password, String firstName, String lastName, genderType gender) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
    }

    // used for write to database while id will be set when created in dao
    public Users(String userName, String password, String firstName, String lastName, genderType gender) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return userId == users.userId && userName.equals(users.userName) && password.equals(
            users.password) && firstName.equals(users.firstName) && lastName.equals(users.lastName)
            && gender == users.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, firstName, lastName, gender);
    }

    @Override
    public String toString() {
        return "Users{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender=" + gender +
            '}';
    }
}
