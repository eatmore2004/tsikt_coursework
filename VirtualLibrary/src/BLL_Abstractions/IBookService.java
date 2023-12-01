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
     * Method to get book by its id
     * @param id
     * @return Result<Book> - book
     */
    Result<Book> getByID(UUID id);

    /**
     * Method to get all books
     * @return Result<List<BaseEntity>>
     */
    Result<List<BaseEntity>> getAll();

    /**
     * Method to get books by its title
     * @param title
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllByTitle(String title);

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
     * Method to get all free books by title
     * @param title
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllFreeByTitle(String title);

    /**
     * Method to get all taken books by title
     * @param title
     * @return Result<List<Book>> - list of books
     */
    Result<List<Book>> getAllTakenByTitle(String title);

    /**
     * Method to rent book
     * @param title
     * @param userId
     * @return Result<String> - message
     */
    Result<String> rentBook(String title, UUID userId);

    /**
     * Method to return book
     * @param bookId
     * @param userId
     * @return Result<String> - message
     */
    Result<String> returnBook(UUID bookId, UUID userId);

    /**
     * Method to lend book from one user to another
     * @param bookId
     * @param ownerId
     * @param userId
     * @return Result<String> - message
     */
    Result<String> lendBook(UUID bookId, UUID ownerId, UUID userId);

    /**
     * Method to add book
     *
     * @param title
     * @param genre
     * @param author
     * @param year
     * @param pages
     * @return Result<String> - message
     */
    Result<UUID> addBook(String title, String genre, String author, int year, int pages);

    /**
     * Method to delete book by its UUID
     * @param id
     * @return Result<String> - message
     */
    Result<String> deleteByID(UUID id);

    /**
     * Method to delete book by its owner
     * @param ownerId
     * @return Result<String> - message
     */
    Result<String> returnAllByOwner(UUID ownerId);
}
