package implementation.database.entity;

import abstraction.database.EntityInterface;

/**
 * A class representing Implementation.database.object.Book
 */
public class Book implements EntityInterface {
    /**
     * Coefficient which makes id unique for each book
     */
    private static int ID_coefficient;

    /**
     * Properties of books
     */
    public final String title;
    public String notes;
    public Author author;
    public Genre genre;
    public final double price;
    public final int ID;
    public boolean booked;
    public String bookerName;

    /**
     * Creates a book
     */
    public Book(String title, Genre genre, Author author, double price) {
        ID = ++ID_coefficient;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
        this.notes = "";
    }

    @Override
    public String getKey() {
        return title+author.getKey();
    }

    public void setNotes(String note) {
        this.notes = note;
    }
}