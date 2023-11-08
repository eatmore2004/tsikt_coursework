package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    Result<Book> getByTitle(String title);
    Result<List<BaseEntity>> getAll();
    Result<List<Book>> getAllByAuthor(String author);
    Result<List<Book>> getAllByGenre(String genre);
    Result<List<Book>> getAllByYear(int year);
    Result<List<Book>> getAllByPages(int pages);
    Result<String> rentBook(String title, UUID userId);
    Result<String> returnBook(UUID bookId);
    Result<String> addBook(String title, String genre, String author, int year, int pages);
    Result<String> editBook(String title, String newTitle, String genre, String author, int year, int pages);
    Result<String> deleteByTitle(String title);
}
