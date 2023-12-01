/**
 * Created by Andrii Yeremenko on 12/1/23.
 */

package BLL;

import Core.Models.Book;
import Core.Models.Result;
import DAL.Repository.Repository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    BookService bookService = new BookService(new Repository(Book.class));

    @Test
    void testAddAndDeleteBook() {
        String title = "Test";
        String author = "Test";
        String genre = "Test";
        int publicationYear = 2048;
        int pageCount = 100;

        Result<UUID> addResult = bookService.addBook(title, author, genre, publicationYear, pageCount);

        assertTrue(addResult.getSuccess());
        UUID BOOK_TEST_ID = addResult.getData();

        Result<String> deleteResult = bookService.deleteByID(BOOK_TEST_ID);

        assertTrue(deleteResult.getSuccess());

        Result<String> secondDeleteResult = bookService.deleteByID(BOOK_TEST_ID);
        assertFalse(secondDeleteResult.getSuccess());
    }

    @Test
    void testBorrowAndReturnBook(){
        String title = "Test009";
        String author = "Test";
        String genre = "Test";
        int publicationYear = 2048;
        int pageCount = 100;

        Result<UUID> addResult = bookService.addBook(title, author, genre, publicationYear, pageCount);

        assertTrue(addResult.getSuccess());
        UUID BOOK_TEST_ID = addResult.getData();

        UUID userId = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();

        Result<String> rentResult = bookService.rentBook(title, userId);
        assertTrue(rentResult.getSuccess());

        Result<String> rentResult2 = bookService.rentBook(title, user2Id);
        assertFalse(rentResult2.getSuccess());

        Result<String> lendResult = bookService.lendBook(BOOK_TEST_ID, userId, user2Id);
        assertTrue(lendResult.getSuccess());

        Result<String> lendResult2 = bookService.lendBook(BOOK_TEST_ID, userId, user2Id);
        assertFalse(lendResult2.getSuccess());

        Result<String> returnResult = bookService.returnBook(BOOK_TEST_ID, user2Id);
        assertTrue(returnResult.getSuccess());

        Result<String> returnResult2 = bookService.returnBook(BOOK_TEST_ID, user2Id);
        assertFalse(returnResult2.getSuccess());

        Result<String> deleteResult = bookService.deleteByID(BOOK_TEST_ID);
        assertTrue(deleteResult.getSuccess());
    }

}