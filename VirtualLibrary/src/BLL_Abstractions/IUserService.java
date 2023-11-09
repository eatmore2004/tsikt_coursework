package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;

import java.util.List;

public interface IUserService extends IGenericService{
    Result<List<BaseEntity>> getAll();
    Result<List<User>> getAllByFirstName(String firstName);
    Result<List<User>> getAllByLastName(String lastName);
    Result<List<User>> getAllByEmail(String email);
    Result<String> changePassword(User user, String newPassword);
    Result<String> changeEmail(User user, String newEmail);
    Result<String> changeFirstName(User user, String newFirstName);
    Result<String> changeLastName(User user, String newLastName);
    Result<String> registerUser(String name, String surname, String username, String email, String password);
    Result<User> loginUser(String username, String password);
    Result<String> deleteUser(String username);
}
