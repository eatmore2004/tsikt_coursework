package BLL;

import BLL_Abstractions.IBookService;
import Core.Models.BaseEntity;
import Core.Models.Book;
import Core.Models.Result;
import DAL_Abstractions.IRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookService extends GenericService implements IBookService{
    public BookService(IRepository repository) {
        super(repository);
    }

    @Override
    public Result<List<BaseEntity>> getAll() {
        return GetAll();
    }

    @Override
    public Result<Book> getByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return new Result<>("Title cannot be empty", false);
        }
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(x -> x.getTitle().equals(title)).toList();
            if (books.isEmpty()) {
                return new Result<>("Such book not found", false);
            } else {
                return new Result<>(books.get(0), "Book found!", true);
            }
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<Book>> getAllByAuthor(String author) {
        if (author == null || author.isEmpty()) return new Result<>("Author cannot be empty", false);
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(x -> x.getAuthor().equals(author)).collect(Collectors.toList());
            return new Result<>(books,
                    (books.isEmpty())? "Such books not found" : "Books found!",true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<Book>> getAllByGenre(String genre) {
        if (genre == null || genre.isEmpty()) return new Result<>("Genre cannot be empty", false);
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(x -> x.getGenre().equals(genre)).collect(Collectors.toList());
            return new Result<>(books,
                    (books.isEmpty())? "Such books not found" : "Books found!",true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<Book>> getAllByYear(int year) {
        if (year <= 0) return new Result<>("Year must be >0", false);
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(x -> x.getYear() == year).collect(Collectors.toList());
            return new Result<>(books,
                    (books.isEmpty())? "Such books not found" : "Books found!",true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<List<Book>> getAllByPages(int pages) {
        Result<List<BaseEntity>> result = GetAll();
        if (result.getSuccess()) {
            List<Book> books = result.getData().stream().map(x -> (Book) x)
                    .filter(x -> x.getPages() == pages).collect(Collectors.toList());
            return new Result<>(books,
                    (books.isEmpty())? "Such books not found" : "Books found!",true);
        } else {
            return new Result<>(result.getMessage(), false);
        }
    }

    @Override
    public Result<String> rentBook(String title, UUID userId) {

        if (title.isEmpty() || userId == null) return new Result<>("Invalid input", false);

        Result<Book> book_result = getByTitle(title);
        if (!book_result.getSuccess()){
            return new Result<>(book_result.getMessage(), false);
        }

        Book book = book_result.getData();
        if (book.getRentedBy() != null) {
            return new Result<>("Book is already rented", false);
        }

        book.setRentedBy(userId);
        book.setRentedAt(new Date().toString());
        return Edit(book);
    }

    @Override
    public Result<String> returnBook(String title, UUID userId) {
        if (title.isEmpty() || userId == null) return new Result<>("Invalid input", false);

        Result<Book> book_result = getByTitle(title);
        if (!book_result.getSuccess()){
            return new Result<>(book_result.getMessage(), false);
        }

        Book book = book_result.getData();
        if (book.getRentedBy() == null) {
            return new Result<>("Book is not rented", false);
        }

        book.setRentedAt(null);
        book.setRentedBy(null);
        return Edit(book);
    }

    @Override
    public Result<String> addBook(String title, String genre, String author, int year, int pages) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || author == null
                || author.isEmpty() || year <= 0 || pages <= 0) {
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
    public Result<String> editBook(String title, String newTitle, String genre, String author, int year, int pages) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || author == null
                || author.isEmpty() || year <= 0 || pages <= 0) {
            return new Result<>("Invalid input", false);
        }

        Result<Book> result = getByTitle(title);

        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        Book bookToEdit = result.getData();
        bookToEdit.setTitle(newTitle);
        bookToEdit.setGenre(genre);
        bookToEdit.setAuthor(author);
        bookToEdit.setYear(year);
        bookToEdit.setPages(pages);
        bookToEdit.setRentedAt(null);
        bookToEdit.setRentedBy(null);

        Result<String> editResult = Edit(bookToEdit);

        if (editResult.getSuccess()) {
            return new Result<>("Book edited successfully", true);
        } else {
            return new Result<>("Book could not be edited",editResult.getMessage(), false);
        }
    }

    @Override
    public Result<String> deleteByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return new Result<>("Invalid input", false);
        }
        Result<Book> result = getByTitle(title);
        if (!result.getSuccess()) return new Result<>(result.getMessage(), false);

        Result<String> deleteResult = Delete(result.getData());

        if (deleteResult.getSuccess()) {
            return new Result<>("Book deleted successfully", true);
        } else {
            return new Result<>("Book could not be deleted",deleteResult.getMessage(), false);
        }
    }
}
