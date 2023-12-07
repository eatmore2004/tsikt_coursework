/**
 * Created by Ihor Rohatiuk on 12/5/23.
 */
package com.lib.virtuallibrary.Models;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;

import java.util.UUID;

/**
 *  Session class. Using to remember user information after log-in
 */
public class Session {

    private static User user;
    private static IUserService userService;

    /**
     *  Session constructor. Using to define services which will be used after initialization
     */
    public Session(){
        userService = new UserService(new Repository(User.class), new BookService(new Repository(Book.class)));
    }

    /**
     * getUser method. Using to get user information
     * @return User class if user exist or "User logged out." message if user doesn't exist
     */
    public static Result<User> getUser() {
        if (user == null) return new Result<>("User logged out.",false);
        return new Result<>(user,true);
    }

    /**
     * logout method. Using to log out user from current session
     */
    public static void logout(){
        user = null;
    }

    /**
     * login method. Using to log in user in current session
     * @param uuid specifying which user should be logged in
     */
    public static void login(UUID uuid){
        Result<User> result_user = userService.getByID(uuid);
        if (result_user.getSuccess()){
            user = result_user.getData();
        } else throw new IllegalArgumentException();
    }

}
