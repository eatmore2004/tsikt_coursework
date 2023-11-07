package BLL;

import BLL_Abstractions.IBookService;
import Core.Models.Book;
import Core.Models.Result;
import DAL_Abstractions.IRepository;

import java.util.List;
import java.util.UUID;

public class BookService extends GenericService implements IBookService{
    public BookService(IRepository repository) {
        super(repository);
    }

    @Override
    public List<Book> getAllByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getAllByGenre(String genre) {
        return null;
    }

    @Override
    public List<Book> getAllByYear(int year) {
        return null;
    }

    @Override
    public List<Book> getAllByPages(int pages) {
        return null;
    }

    @Override
    public Result<String> rentBook(String title, UUID userId) {
        return null;
    }

    @Override
    public Result<String> returnBook(UUID bookId) {
        return null;
    }

    @Override
    public Result<String> addBook(String title, String genre, String author, int year, int pages) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || author == null
                || author.isEmpty() || year < 0 || pages < 0) {
            return new Result<>("Invalid input", false);
        }
        Book book = new Book(title, genre, author, year, pages);
        Result<String> result = Add(book);
        if (result.getSuccess()) {
            return new Result<>("Book added successfully", true);
        } else {
            return new Result<>("Book could not be added",result.getMessage(), false);
        }
    }

    @Override
    public Result<String> editBook(String title, Book book) {
        return null;
    }

    @Override
    public Result<String> deleteByTitle(String title) {
        return null;
    }
}
