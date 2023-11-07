package BLL_Abstractions;

import Core.Models.User;

import java.util.List;

public interface IUserService {
    List<User> getAllByFirstName(String firstName);
    List<User> getAllByLastName(String lastName);
    List<User> getAllByEmail(String email);
    List<User> getAllByPhoneNumber(String phoneNumber);
    void changePassword(User user, String newPassword);
    void changeEmail(User user, String newEmail);
    void changePhoneNumber(User user, String newPhoneNumber);
    void changeFirstName(User user, String newFirstName);
    void changeLastName(User user, String newLastName);
    void changeUsername(User user, String newUsername);
    void registerUser(User user);
    User loginUser(String username, String password);
}
