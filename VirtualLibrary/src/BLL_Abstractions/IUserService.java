package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;

import java.util.List;

/**
 * IUserService interface
 * created by Andrii Yeremenko
 */
public interface IUserService extends IGenericService{

    /**
     * Method to get all users
     * @return Result<List<BaseEntity>>
     */
    Result<List<BaseEntity>> getAll();

    /**
     * Method to get all users by first name
     * @param firstName
     * @return Result<List<User>> - list of users
     */
    Result<List<User>> getAllByFirstName(String firstName);

    /**
     * Method to get all users by last name
     * @param lastName
     * @return Result<List<User>> - list of users
     */
    Result<List<User>> getAllByLastName(String lastName);

    /**
     * Method to get all users by email
     * @param email
     * @return Result<List<User>> - list of users
     */
    Result<List<User>> getAllByEmail(String email);

    /**
     * Method to change user password
     * @param user
     * @param newPassword
     * @return Result<String> - message
     */
    Result<String> changePassword(User user, String newPassword);

    /**
     * Method to change user email
     * @param user
     * @param newEmail
     * @return Result<String> - message
     */
    Result<String> changeEmail(User user, String newEmail);

    /**
     * Method to change user first name
     * @param user
     * @param newFirstName
     * @return Result<String> - message
     */
    Result<String> changeFirstName(User user, String newFirstName);

    /**
     * Method to change user last name
     * @param user
     * @param newLastName
     * @return Result<String> - message
     */
    Result<String> changeLastName(User user, String newLastName);

    /**
     * Method to register new user
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param password
     * @return Result<String> - message
     */
    Result<String> registerUser(String name, String surname, String username, String email, String password);

    /**
     * Method to login user
     * @param username
     * @param password
     * @return Result<User> - user
     */
    Result<User> loginUser(String username, String password);

    /**
     * Method to delete user
     * @param username
     * @return Result<String> - message
     */
    Result<String> deleteUser(String username);
}
