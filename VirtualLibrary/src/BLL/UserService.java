package BLL;

import BLL_Abstractions.IUserService;
import Core.Models.User;

import java.util.List;
import java.util.function.Predicate;

public class UserService implements IUserService {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllByPredicate(Predicate<User> predicate) {
        return null;
    }

    @Override
    public void saveAll(List<User> items) {

    }

    @Override
    public List<User> getAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> getAllByLastName(String lastName) {
        return null;
    }

    @Override
    public List<User> getAllByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAllByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) {

    }

    @Override
    public void changeEmail(User user, String newEmail) {

    }

    @Override
    public void changePhoneNumber(User user, String newPhoneNumber) {

    }

    @Override
    public void changeFirstName(User user, String newFirstName) {

    }

    @Override
    public void changeLastName(User user, String newLastName) {

    }

    @Override
    public void changeUsername(User user, String newUsername) {

    }

    @Override
    public void registerUser(User user) {

    }

    @Override
    public void loginUser(String username, String password) {

    }

    @Override
    public void logoutUser() {

    }
}
