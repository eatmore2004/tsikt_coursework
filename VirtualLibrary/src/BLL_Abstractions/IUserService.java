/**
 * Created by Andrii Yeremenko on 11/6/23.
 */

package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface IUserService extends IGenericService{

    /**
     * Method to get user by its id
     * @param id
     * @return Result<User> - user
     */
    Result<User> getByID(UUID id);

    /**
     * Method to get all users
     * @return Result<List<BaseEntity>>
     */
    Result<List<BaseEntity>> getAll();

    /**
     * Method to get all users by predicate
     * @param predicate
     * @param <T>
     * @return Result<T> - list of users
     */
    <T> Result<T> getAllByPredicate(Predicate<User> predicate);

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
     * @param userId
     * @param newPassword
     * @return Result<String> - message
     */
    Result<String> changePassword(UUID userId, String newPassword);

    /**
     * Method to change user email
     * @param userId
     * @param newEmail
     * @return Result<String> - message
     */
    Result<String> changeEmail(UUID userId, String newEmail);

    /**
     * Method to change user first name
     * @param userId
     * @param newFirstName
     * @return Result<String> - message
     */
    Result<String> changeFirstName(UUID userId, String newFirstName);

    /**
     * Method to change user last name
     * @param userId
     * @param newLastName
     * @return Result<String> - message
     */
    Result<String> changeLastName(UUID userId, String newLastName);

    /**
     * Method to register new user
     *
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param password
     * @return Result<String> - message
     */
    Result<UUID> registerUser(String name, String surname, String username, String email, String password);

    /**
     * Method to login user
     * @param username
     * @param password
     * @return Result<User> - user
     */
    Result<User> loginUser(String username, String password);

    /**
     * Method to delete user
     * @param userId
     * @return Result<String> - message
     */
    Result<String> deleteUser(UUID userId);

    Result<User> getByUsername(String username);
}
