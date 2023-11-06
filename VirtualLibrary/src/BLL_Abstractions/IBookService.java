package BLL_Abstractions;

import Core.Models.Book;

import java.util.List;

public interface IBookService extends IService<Book> {
    List<Book> getAllByAuthor(String author);
    List<Book> getAllByGenre(String genre);
    List<Book> getAllByYear(int year);
    List<Book> getAllByPages(int pages);
    void rentBook(Book book, String userId);
    void returnBook(Book book);
}
