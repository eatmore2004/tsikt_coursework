package BLL;

import BLL_Abstractions.IBookService;
import Core.Models.Book;

import java.util.List;
import java.util.function.Predicate;

public class BookService implements IBookService {
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
    public void rentBook(Book book, String userId) {

    }

    @Override
    public void returnBook(Book book) {

    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAllByPredicate(Predicate<Book> predicate) {
        return null;
    }

    @Override
    public void saveAll(List<Book> items) {

    }
}
