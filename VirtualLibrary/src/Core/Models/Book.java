package Core.Models;

import java.util.UUID;

/**
 * Book class
 * created by Andrii Yeremenko
 */
public class Book extends BaseEntity {
    private String title;
    private String genre;
    private String author;
    private int year;
    private int pages;
    private String rentedAt;
    private UUID rentedBy;

    /**
     * Constructor
     */
    public Book() {
    }

    /**
     * Constructor. On creation creates new instance, using passed parameters
     * @param title
     * @param genre
     * @param author
     * @param year
     * @param pages
     */
    public Book(String title, String genre, String author, int year, int pages) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(String rentedAt) {
        this.rentedAt = rentedAt;
    }

    public UUID getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(UUID rentedBy) {
        this.rentedBy = rentedBy;
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Generates string representation of Book object
     * @return String - string representation of Book object
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", rentedAt=" + rentedAt +
                ", rentedBy=" + rentedBy +
                '}';
    }
}
