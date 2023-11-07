package BLL_Abstractions;

import Core.Models.Book;
import Core.Models.Result;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    List<Book> getAllByAuthor(String author);
    List<Book> getAllByGenre(String genre);
    List<Book> getAllByYear(int year);
    List<Book> getAllByPages(int pages);
    Result<String> rentBook(String title, UUID userId);
    Result<String> returnBook(UUID bookId);
    Result<String> addBook(String title, String genre, String author, int year, int pages);
    Result<String> editBook(String title, Book book);
    Result<String> deleteByTitle(String title);
}
