/**
 * Created by Andrii Yeremenko on 11/6/23.
 */

package BLL;

import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;
import DAL_Abstractions.IRepository;
import Security.PasswordHashing;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * UserService class. Implements IUserService interface
 * @see IUserService
 */
public class UserService extends GenericService implements IUserService {

    private final IBookService bookService;

    public UserService(IRepository userRepository, IBookService bookService){
        super(userRepository);
        this.bookService = bookService;
    }


    @Override
    public Result<User> getByID(UUID id) {
        if (id == null) {
            return new Result<>("Invalid input", false);
        }

        Result<List<User>> result = getAllByPredicate(x -> x.getId().equals(id));

        if (result.getSuccess()) {
            return new Result<>(result.getData().get(0), true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<BaseEntity>> getAll() {
        return GetAll();
    }

    public <T> Result<T> getAllByPredicate(Predicate<User> predicate) {
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<User> books = result.getData().stream().map(x -> (User) x)
                    .filter(predicate).collect(Collectors.toList());

            if (books.isEmpty()) return new Result<>("No such users", false);
            return (Result<T>) new Result<>(books, true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }


    @Override
    public Result<List<User>> getAllByFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return new Result<>("First name cannot be empty", false);
        }
        return getAllByPredicate(x -> x.getName().equals(firstName));
    }

    @Override
    public Result<List<User>> getAllByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return new Result<>("Last name cannot be empty", false);
        }
        return getAllByPredicate(x -> x.getSurname().equals(lastName));
    }

    @Override
    public Result<List<User>> getAllByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return new Result<>("Email cannot be empty", false);
        }
        return getAllByPredicate(x -> x.getEmail().equals(email));
    }

    @Override
    public Result<String> changePassword(UUID userId, String newPassword) {
        Result<User> result = getByID(userId);
        if (result.getSuccess()){
            User user = result.getData();
            try {
                user.changePasswordHash(PasswordHashing.hashPassword(newPassword));
                return Edit(user);
            } catch (Exception e) {
                return new Result<>(e.getMessage(), false);
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> changeEmail(UUID userId, String newEmail) {
        Result<User> result = getByID(userId);
        if (result.getSuccess()){
            User user = result.getData();
            user.setEmail(newEmail);
            return Edit(user);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> changeFirstName(UUID userId, String newFirstName) {
        Result<User> result = getByID(userId);
        if (result.getSuccess()){
            User user = result.getData();
            user.setName(newFirstName);
            return Edit(user);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> changeLastName(UUID userId, String newLastName) {
        Result<User> result = getByID(userId);
        if (result.getSuccess()){
            User user = result.getData();
            user.setSurname(newLastName);
            return Edit(user);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<UUID> registerUser(String name, String surname, String username, String email, String password) {
        if (name == null || name.isEmpty()) {
            return new Result<>("Name cannot be empty", false);
        }
        if (surname == null || surname.isEmpty()) {
            return new Result<>("Surname cannot be empty", false);
        }
        if (username == null || username.isEmpty()) {
            return new Result<>("Username cannot be empty", false);
        }
        if (email == null || email.isEmpty()) {
            return new Result<>("Email cannot be empty", false);
        }
        if (password == null || password.isEmpty()) {
            return new Result<>("Password cannot be empty", false);
        }

        String passwordHash;

        try {
            passwordHash = PasswordHashing.hashPassword(password);
        } catch (Exception e) {
            return new Result<>(e.getMessage(), false);
        }

        User user = new User(UUID.randomUUID(), name, surname, username, email, passwordHash);

        Result<String> result = Add(user);
        if (result.getSuccess()) {
            return new Result<>(user.getId(), result.getMessage(), true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<User> loginUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            return new Result<>("Username cannot be empty", false);
        }
        if (password == null || password.isEmpty()) {
            return new Result<>("Password cannot be empty", false);
        }

        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()){
            List<User> users = result.getData().stream().map(x -> (User) x)
                    .filter(x -> x.getUsername().equals(username)).toList();
            if (users.isEmpty()){
                return new Result<>("Such users not found", false);
            } else {
                User user = users.get(0);
                try {
                    if (PasswordHashing.verifyPassword(password, user.getPasswordHash())){
                        return new Result<>(user, true);
                    } else {
                        return new Result<>("Wrong password", false);
                    }
                } catch (Exception e) {
                    return new Result<>(e.getMessage(), false);
                }
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    public Result<String> deleteUser(UUID userId){
        if (userId == null) {
            return new Result<>("Invalid input", false);
        }

        Result<User> user = getByID(userId);
        if (user.getSuccess()){
            Result<String> returnedAllByOwner = bookService.returnAllByOwner(user.getData().getId());
            Result<String> deletedUser = Delete(user.getData());
            return new Result<>(returnedAllByOwner.getMessage() + "\n" + deletedUser.getMessage(), deletedUser.getSuccess());
        } else {
            return new Result<>(user.getMessage(), false);
        }
    }

    @Override
    public Result<User> getByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return new Result<>("First name cannot be empty", false);
        }
        Result<List<User>> result = getAllByPredicate(x -> x.getUsername().equals(username));

        if (result.getSuccess()) {
            return new Result<>(result.getData().get(0), true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }
}
