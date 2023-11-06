package Core.Models;

import java.util.Date;
import java.util.UUID;

public class Book extends BaseEntity {
    private final String title;
    private final String genre;
    private final int year;
    private final int pages;
    private Date rentedAt;
    private UUID rentedBy;

    public Book(String title, String author, String genre, int year, int pages) {
        super();
        this.title = title;
        this.genre = genre;
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

    public Date getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(Date rentedAt) {
        this.rentedAt = rentedAt;
    }

    public UUID getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(UUID rentedBy) {
        this.rentedBy = rentedBy;
    }

    private void Rent(UUID userId) {
        this.rentedAt = new Date();
        this.rentedBy = userId;
    }

    private void Return() {
        this.rentedBy = null;
    }
}
