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


        IBookService bookService = new BookService(new Repository(Book.class));
        IUserService userService = new UserService(new Repository(User.class), bookService);

        bookService.addBook("Good Omens", "Fantasy", "Terry Pratchett", 1990, 500);
    }
}
