package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;

import java.util.List;

public interface IUserService {
    Result<List<BaseEntity>> getAll();
    Result<List<User>> getAllByFirstName(String firstName);
    Result<List<User>> getAllByLastName(String lastName);
    Result<List<User>> getAllByEmail(String email);
    Result<List<User>> getAllByPhoneNumber(String phoneNumber);
    Result<String> changePassword(User user, String newPassword);
    Result<String> changeEmail(User user, String newEmail);
    Result<String> changePhoneNumber(User user, String newPhoneNumber);
    Result<String> changeFirstName(User user, String newFirstName);
    Result<String> changeLastName(User user, String newLastName);
    Result<String> changeUsername(User user, String newUsername);
    Result<String> registerUser(User user);
    Result<User> loginUser(String username, String password);
}
