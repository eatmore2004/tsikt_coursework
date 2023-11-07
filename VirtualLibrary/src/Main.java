import BLL.BookService;
import BLL_Abstractions.IBookService;
import Core.Models.Book;
import Core.Models.Result;
import DAL.Repository.Repository;

import java.util.IllegalFormatCodePointException;


public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService(new Repository(Book.class));
        Result<String> result = bookService.addBook("Harry Potter 5", "Fantasy", "J.K Rowling", 2020, 100);
        System.out.println(result.getData());
        if (!result.getSuccess()) {
            System.out.println(result.getMessage());
        }
    }
}
