package BLL_Abstractions;

import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;

import java.util.List;
import java.util.UUID;

/**
 * IBookService interface
 * created by Andrii Yeremenko
 */
public interface IBookService {

    /**
     * Method to get book by title
     * @param title
     * @return Result<Book>
     */
    Result<Book> getByTitle(String title);

    /**
     * Method to get all books
     * @return Result<List<BaseEntity>>
     */
    Result<List<BaseEntity>> getAll();

    /**
     * Method to get all books by author
     * @param author
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByAuthor(String author);

    /**
     * Method to get all books by genre
     * @param genre
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByGenre(String genre);

    /**
     * Method to get all books by year
     * @param year
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByYear(int year);

    /**
     * Method to get all books by pages
     * @param pages
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByPages(int pages);

    /**
     * Method to get all books by owner
     * @param ownerId
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByOwner(UUID ownerId);

    /**
     * Method to get all books that have not been taken
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllNotTaken();

    /**
     * Method to get all books that have been taken
     * @return
     */
    Result<List<Book>> getAllTaken();

    /**
     * Method to rent book
     * @param title
     * @param userId
     * @return Result<String> - message
     */
    Result<String> rentBook(String title, UUID userId);

    /**
     * Method to return book
     * @param title
     * @param userId
     * @return Result<String> - message
     */
    Result<String> returnBook(String title, UUID userId);

    /**
     * Method to lend book from one user to another
     * @param title
     * @param ownerId
     * @param userId
     * @return Result<String> - message
     */
    Result<String> lendBook(String title, UUID ownerId, UUID userId);

    /**
     * Method to add book
     * @param title
     * @param genre
     * @param author
     * @param year
     * @param pages
     * @return Result<String> - message
     */
    Result<String> addBook(String title, String genre, String author, int year, int pages);

    /**
     * Method to edit book
     * @param title
     * @param newTitle
     * @param genre
     * @param author
     * @param year
     * @param pages
     * @return Result<String> - message
     */
    Result<String> editBook(String title, String newTitle, String genre, String author, int year, int pages);

    /**
     * Method to delete book by its title
     * @param title
     * @return Result<String> - message
     */
    Result<String> deleteByTitle(String title);
}
