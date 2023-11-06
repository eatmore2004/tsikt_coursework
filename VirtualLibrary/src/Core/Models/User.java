package Core.Models;

public class User extends BaseEntity{

    private String name;
    private String surname;
    private final String username;
    private String email;
    private String passwordHash;

    public User(String name, String surname, String username, String email, String passwordHash) {
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

    public boolean checkPassword(String password) {
        return this.passwordHash.equals(password);
    }

    public void changePassword(String password) {
        this.passwordHash = password;
    }
}