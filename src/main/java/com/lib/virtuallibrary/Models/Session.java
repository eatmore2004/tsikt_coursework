package com.lib.virtuallibrary.Models;

import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IUserService;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;

import java.util.UUID;

public class Session {

    private static User user;
    private static IUserService userService;

    public Session(){
        userService = new UserService(new Repository(User.class), new BookService(new Repository(Book.class)));
    }

    public Session(UUID uuid){
        userService = new UserService(new Repository(User.class), new BookService(new Repository(Book.class)));
        login(uuid);
    }

    public static Result<User> getUser() {
        if (user == null) return new Result<>("User logged out.",false);
        return new Result<>(user,true);
    }

    public static void logout(){
        user = null;
    }

    public static void login(UUID uuid){
        Result<User> result_user = userService.getByID(uuid);
        if (result_user.getSuccess()){
            user = result_user.getData();
        } else throw new IllegalArgumentException();
    }

}
