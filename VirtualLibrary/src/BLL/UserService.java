package BLL;

import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Result;
import Core.Models.User;
import DAL_Abstractions.IRepository;

import java.util.List;

public class UserService extends GenericService implements IUserService {

    public UserService(IRepository repository) {
        super(repository);
    }


    @Override
    public Result<List<BaseEntity>> getAll() {
        return null;
    }

    @Override
    public Result<List<User>> getAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public Result<List<User>> getAllByLastName(String lastName) {
        return null;
    }

    @Override
    public Result<List<User>> getAllByEmail(String email) {
        return null;
    }

    @Override
    public Result<List<User>> getAllByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Result<String> changePassword(User user, String newPassword) {
        return null;
    }

    @Override
    public Result<String> changeEmail(User user, String newEmail) {
        return null;
    }

    @Override
    public Result<String> changePhoneNumber(User user, String newPhoneNumber) {
        return null;
    }

    @Override
    public Result<String> changeFirstName(User user, String newFirstName) {
        return null;
    }

    @Override
    public Result<String> changeLastName(User user, String newLastName) {
        return null;
    }

    @Override
    public Result<String> changeUsername(User user, String newUsername) {
        return null;
    }

    @Override
    public Result<String> registerUser(User user) {
        return null;
    }

    @Override
    public Result<User> loginUser(String username, String password) {
        return null;
    }
}
