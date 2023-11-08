import BLL.BookService;
import BLL.UserService;
import BLL_Abstractions.IBookService;
import BLL_Abstractions.IUserService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import Core.Models.User;
import DAL.Repository.Repository;

import java.util.IllegalFormatCodePointException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService(new Repository(Book.class));
        UserService userService = new UserService(new Repository(User.class));

        Result<Book> books = bookService.getByTitle("The Lord of the Rings");
        System.out.println(books.getSuccess());

        System.out.println(books.getData().getTitle());

    }
}
