package Core.Models;

import java.util.UUID;

/**
 * User class
 * created by Andrii Yeremenko
 */
public class User extends BaseEntity{

    private String name;
    private String surname;
    private String username;
    private String email;
    private String passwordHash;

    /**
     * Empty Constructor
     */
    public User() {
    }

    /**
     * Constructor. On creation creates new instance with random UUID, using passed parameters
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param passwordHash
     */
    public User(String name, String surname, String username, String email, String passwordHash) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Constructor. On creation creates new instance, using passed parameters
     * @param id
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param passwordHash
     */
    public User(UUID id, String name, String surname, String username, String email, String passwordHash) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void changePasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Method for getting string representation of User
     * @return String - string representation of User
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname=" + surname +
                ", username=" + username +
                ", email=" + email +
                ", passwordHash=" + passwordHash +
                '}';
    }
}
