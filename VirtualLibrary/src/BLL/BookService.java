/**
 * Created by Andrii Yeremenko on 11/6/23.
 */

package BLL;

import BLL_Abstractions.IBookService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import DAL_Abstractions.IRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * BookService class implements IBookService interface
 * @see IBookService
 */
public class BookService extends GenericService implements IBookService{

    /**
     * Constructor
     * @param repository - IRepository object
     */
    public BookService(IRepository repository) {
        super(repository);
    }

    @Override
    public Result<List<BaseEntity>> getAll() {
        return GetAll();
    }

    public <T> Result<T> getAllByPredicate(Predicate<Book> predicate) {
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(predicate).collect(Collectors.toList());

            if (books.isEmpty()) return new Result<>("No such books", false);
            return (Result<T>) new Result<>(books, true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<Book> getByID(UUID id) {
        if (id == null) {
            return new Result<>("Invalid input", false);
        }

        Result<List<Book>> result = getAllByPredicate(x -> x.getId().equals(id));

        if (result.getSuccess()) {
            return new Result<>(result.getData().get(0), true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<Book>> getAllByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return new Result<>("Title cannot be empty", false);
        }
        return getAllByPredicate(x -> x.getTitle().equals(title));
    }

    @Override
    public Result<List<Book>> getAllByAuthor(String author) {
        if (author == null || author.isEmpty()) return new Result<>("Author cannot be empty", false);
        return getAllByPredicate(x -> x.getAuthor().equals(author));
    }

    @Override
    public Result<List<Book>> getAllByGenre(String genre) {
        if (genre == null || genre.isEmpty()) return new Result<>("Genre cannot be empty", false);
        return getAllByPredicate(x -> x.getGenre().equals(genre));
    }

    @Override
    public Result<List<Book>> getAllByYear(int year) {
        if (year <= 0) return new Result<>("Year must be >0", false);
        return getAllByPredicate(x -> x.getYear() == year);
    }

    @Override
    public Result<List<Book>> getAllByPages(int pages) {
        Result<List<BaseEntity>> result = GetAll();
        return getAllByPredicate(x -> x.getPages() == pages);
    }

    @Override
    public Result<List<Book>> getAllByOwner(UUID ownerId) {
        if (ownerId == null) return new Result<>("Invalid input", false);
        Result<List<Book>> result = getAllTaken();
        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);
        List<Book> books = result.getData().stream().map(x -> (Book) x)
                .filter(x -> x.getRentedBy().equals(ownerId)).collect(Collectors.toList());

        return new Result<>(books, true);
    }

    @Override
    public Result<List<Book>> getAllNotTaken() {
        Result<List<BaseEntity>> result = GetAll();
        return getAllByPredicate(x -> x.getRentedBy() == null);
    }

    @Override
    public Result<List<Book>> getAllTaken() {
        Result<List<BaseEntity>> result = GetAll();
        return getAllByPredicate(x -> x.getRentedBy() != null);
    }

    @Override
    public Result<List<Book>> getAllFreeByTitle(String title) {
        if (title == null || title.isEmpty()) return new Result<>("Title cannot be empty", false);
        Result<List<Book>> result = getAllByTitle(title);

        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        List<Book> books = result.getData().stream().filter(x -> x.getRentedBy() == null).toList();

        if (books.isEmpty()) return new Result<>("No free books found", false);
        return new Result<>(books, true);
    }

    @Override
    public Result<List<Book>> getAllTakenByTitle(String title) {
        if (title == null || title.isEmpty()) return new Result<>("Title cannot be empty", false);
        Result<List<Book>> result = getAllByTitle(title);

        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        List<Book> books = result.getData().stream().filter(x -> x.getRentedBy() != null).toList();

        if (books.isEmpty()) return new Result<>("No taken books found", false);
        return new Result<>(books, true);
    }

    @Override
    public Result<String> rentBook(String title, UUID userId) {

        if (title.isEmpty() || userId == null) return new Result<>("Invalid input", false);

        Result<List<Book>> book_result = getAllFreeByTitle(title);
        if (!book_result.getSuccess()){
            return new Result<>(book_result.getMessage(), false);
        }

        Book book = book_result.getData().get(0);
        book.setRentedBy(userId);
        book.setRentedAt(new Date().toString());
        return Edit(book);
    }

    @Override
    public Result<String> returnBook(UUID bookId, UUID userId) {
        if (bookId == null || userId == null) return new Result<>("Invalid input", false);

        Result<Book> book_result = getByID(bookId);
        if (!book_result.getSuccess()){
            return new Result<>(book_result.getMessage(), false);
        }

        Book book = book_result.getData();

        if (book.getRentedBy() == null || !book.getRentedBy().equals(userId)) return new Result<>("This book is not rented by you", false);

        book.setRentedBy(null);
        book.setRentedAt(null);
        return Edit(book_result.getData());
    }

    @Override
    public Result<String> lendBook(UUID bookID, UUID ownerId, UUID userId){
        if (bookID == null || userId == null) return new Result<>("Invalid input", false);

        Result<Book> book_result = getByID(bookID);
        if (!book_result.getSuccess()){
            return new Result<>(book_result.getMessage(), false);
        }

        if (!book_result.getData().getRentedBy().equals(ownerId)) return new Result<>("This book is not rented by you", false);

        Book book = book_result.getData();

        if (book.getRentedBy() == null || !book.getRentedBy().equals(ownerId)) return new Result<>("This book is not rented by you", false);

        book.setRentedBy(userId);
        return Edit(book_result.getData());
    }

    @Override
    public Result<UUID> addBook(String title, String genre, String author, int year, int pages) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || author == null
                || author.isEmpty() || year <= 0 || pages <= 0) {
            return new Result<>("Invalid input", false);
        }
        Book book = new Book(title, genre, author, year, pages);
        Result<String> result = Add(book);
        if (result.getSuccess()) {
            return new Result<>(book.getId(),"Book added successfully", true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> deleteByID(UUID id) {
        if (id == null) {
            return new Result<>("Invalid input", false);
        }
        Result<Book> result = getByID(id);
        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        Result<String> deleteResult = Delete(result.getData());

        if (deleteResult.getSuccess()) {
            return new Result<>("Book deleted successfully", true);
        } else {
            return new Result<>("Book could not be deleted",deleteResult.getMessage(), false);
        }
    }

    @Override
    public Result<String> returnAllByOwner(UUID ownerId) {
        if (ownerId == null) {
            return new Result<>("Invalid input", false);
        }
        Result<List<Book>> result = getAllByOwner(ownerId);
        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        if (result.getData().isEmpty()) return new Result<>("No books found", false);

        for (Book book : result.getData()) {
            book.setRentedAt(null);
            book.setRentedBy(null);
            Result<String> deleteResult = Edit(book);
            if (!deleteResult.getSuccess()) return new Result<>(deleteResult.getMessage(), false);
        }

        return new Result<>("Books returned successfully", true);
    }
}
