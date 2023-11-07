package BLL;

import BLL_Abstractions.IUserService;
import Core.Models.User;
import DAL_Abstractions.IRepository;

import java.util.List;

public class UserService extends GenericService implements IUserService {

    public UserService(IRepository repository) {
        super(repository);
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
    public User loginUser(String username, String password) {
        return null;
    }
}
