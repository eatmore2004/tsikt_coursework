package BLL;

import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;
import DAL_Abstractions.IRepository;
import Security.PasswordHashing;

import java.util.List;

/**
 * UserService class. Implements IUserService interface
 * created by Andrii Yeremenko
 * @see IUserService
 */
public class UserService extends GenericService implements IUserService {

    public UserService(IRepository repository) {
        super(repository);
    }


    @Override
    public Result<List<BaseEntity>> getAll() {
        return GetAll();
    }

    @Override
    public Result<List<User>> getAllByFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return new Result<>("First name cannot be empty", false);
        }
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()){
            List<User> users = result.getData().stream().map(x -> (User) x)
                    .filter(x -> x.getName().equals(firstName)).toList();
            if (users.isEmpty()){
                return new Result<>("Such users not found", false);
            } else {
                return new Result<>(users, true);
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<User>> getAllByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return new Result<>("Last name cannot be empty", false);
        }
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()){
            List<User> users = result.getData().stream().map(x -> (User) x)
                    .filter(x -> x.getSurname().equals(lastName)).toList();
            if (users.isEmpty()){
                return new Result<>("Such users not found", false);
            } else {
                return new Result<>(users, true);
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<User>> getAllByEmail(String email) {
        if (email == null || email.isEmpty()) {
            return new Result<>("Email cannot be empty", false);
        }
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()){
            List<User> users = result.getData().stream().map(x -> (User) x)
                    .filter(x -> x.getEmail().equals(email)).toList();
            if (users.isEmpty()){
                return new Result<>("Such users not found", false);
            } else {
                return new Result<>(users, true);
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> changePassword(User user, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return new Result<>("Password cannot be empty", false);
        }
        if (user == null) {
            return new Result<>("User cannot be null", false);
        }
        try {
            user.changePasswordHash(PasswordHashing.hashPassword(newPassword));
        } catch (Exception e) {
            return new Result<>(e.getMessage(), false);
        }

        return Edit(user);
    }

    @Override
    public Result<String> changeEmail(User user, String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            return new Result<>("Email cannot be empty", false);
        }
        if (user == null) {
            return new Result<>("User cannot be null", false);
        }
        try {
            user.setEmail(newEmail);
        } catch (Exception e) {
            return new Result<>(e.getMessage(), false);
        }

        return Edit(user);
    }

    @Override
    public Result<String> changeFirstName(User user, String newFirstName) {
        if (newFirstName == null || newFirstName.isEmpty()) {
            return new Result<>("First name cannot be empty", false);
        }
        if (user == null) {
            return new Result<>("User cannot be null", false);
        }
        try {
            user.setName(newFirstName);
        } catch (Exception e) {
            return new Result<>(e.getMessage(), false);
        }

        return Edit(user);
    }

    @Override
    public Result<String> changeLastName(User user, String newLastName) {
        if (newLastName == null || newLastName.isEmpty()) {
            return new Result<>("Last name cannot be empty", false);
        }
        if (user == null) {
            return new Result<>("User cannot be null", false);
        }
        try {
            user.setSurname(newLastName);
        } catch (Exception e) {
            return new Result<>(e.getMessage(), false);
        }

        return Edit(user);
    }

    @Override
    public Result<String> registerUser(String name, String surname, String username, String email, String password) {
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

        return Add(new User(name, surname, username, email, passwordHash));
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

    public Result<String> deleteUser(String username){
        if (username == null || username.isEmpty()) {
            return new Result<>("Username cannot be empty", false);
        }
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()){
            List<User> users = result.getData().stream().map(x -> (User) x)
                    .filter(x -> x.getUsername().equals(username)).toList();
            if (users.isEmpty()){
                return new Result<>("Such users not found", false);
            } else {
                return Delete(users.get(0));
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }
}
