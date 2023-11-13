import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;

import java.util.List;

public class Main {
    public static void main(String[] args){

        //Existing user Andrushka with password 12345678;

        IUserService userService = new UserService(new Repository(User.class));
        IBookService bookService = new BookService(new Repository(Book.class));

        Result<User> userResult = userService.loginUser("Andrushka", "12345678");
        if (!userResult.getSuccess()) System.out.println(userResult.getMessage());

        Result<String> bookResult = bookService.rentBook("Essencialism", userResult.getData().getId());
        if (!bookResult.getSuccess()) System.out.println(bookResult.getMessage());

        Result<List<BaseEntity>> resultlist = bookService.getAll();
        for (BaseEntity item : resultlist.getData()) {
            System.out.println(item.toString());
        }
    }
}
