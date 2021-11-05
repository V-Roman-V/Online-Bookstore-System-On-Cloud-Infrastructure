package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

/**
 * A class representing Implementation.database.object.Book
 */
public class Book implements EntityInterface {
    /**
     * Coefficient which makes id unique for each book
     */
    private static int ID_coefficient;

    /**
     * Property of books
     * 
     * final means key fuild
     */
    public final String title;
    public final Author author;

    public String note;
    public Genre genre;
    public Double price;

    public final int ID;

    /**
     * Creates a book
     */
    public Book(String title, Author author) {
        ID = ++ID_coefficient;
        this.title = title;
        this.author = author;

        this.genre = null;
        this.price = null;
        this.note = null;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getNote() {
        return note;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String getKey() {
        return title + author.getKey();
    }

    public static String getKey(String title, Author author) {
        return title + author.getKey();
    }
}