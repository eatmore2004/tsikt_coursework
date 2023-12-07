/**
 * Created by Andrii Yeremenko on 01/12/2023
 */

package test.java.BLL;

import BLL.BookService;
import BLL.UserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    BookService bookService = new BookService(new Repository(Book.class));
    UserService userService = new UserService(new Repository(User.class), bookService);
    User test_user = new User("Test", "Test", "Test", "Test", "Test");

    @Test
    void testLoginUser() {
        Result<UUID> addResult = userService.registerUser(test_user.getName(), test_user.getSurname(), test_user.getUsername(), test_user.getEmail(), test_user.getPasswordHash());
        assertTrue(addResult.getSuccess());

        UUID USER_TEST_ID = addResult.getData();

        Result<User> loginResult = userService.loginUser(test_user.getUsername(), test_user.getPasswordHash());
        assertTrue(loginResult.getSuccess());

        Result<String> deleteResult = userService.deleteUser(USER_TEST_ID);
        assertTrue(deleteResult.getSuccess());

        Result<String> secondDeleteResult = userService.deleteUser(USER_TEST_ID);
        assertFalse(secondDeleteResult.getSuccess());
    }

    @Test
    void testEditUser(){
        Result<UUID> addResult = userService.registerUser(test_user.getName(), test_user.getSurname(), test_user.getUsername(), test_user.getEmail(), test_user.getPasswordHash());
        assertTrue(addResult.getSuccess());

        UUID USER_TEST_ID = addResult.getData();

        String newName = "Test2";
        String newSurname = "Test2";
        String newEmail = "Test2";
        String newPasswordHash = "Test2";

        Result<String> editFirstNameResult = userService.changeFirstName(USER_TEST_ID, newName);
        Result<String> editLastNameResult = userService.changeLastName(USER_TEST_ID, newSurname);
        Result<String> editEmailResult = userService.changeEmail(USER_TEST_ID, newEmail);
        Result<String> editPasswordResult = userService.changePassword(USER_TEST_ID, newPasswordHash);

        assertTrue(editFirstNameResult.getSuccess());
        assertTrue(editLastNameResult.getSuccess());
        assertTrue(editEmailResult.getSuccess());
        assertTrue(editPasswordResult.getSuccess());

        Result<User> loginResult = userService.loginUser(test_user.getUsername(), newPasswordHash);
        assertTrue(loginResult.getSuccess());

        Result<String> deleteResult = userService.deleteUser(USER_TEST_ID);
        assertTrue(deleteResult.getSuccess());

        Result<String> secondDeleteResult = userService.deleteUser(USER_TEST_ID);
        assertFalse(secondDeleteResult.getSuccess());
    }
}