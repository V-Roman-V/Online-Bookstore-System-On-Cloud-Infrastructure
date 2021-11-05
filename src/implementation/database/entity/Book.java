package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

import static jdk.javadoc.internal.doclets.toolkit.util.Utils.toLowerCase;

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
    private final String title;
    private final Author author;

    private String note;
    private Genre genre;
    private Double price;

    private final int ID;

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

    //Below is the implementation of Builder pattern

    public Book genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public Book note(String note) {
        this.note = note;
        return this;
    }

    public Book price(Double price) {
        this.price = price;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    //Below are getters which returns private fields of Book class

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
        return toLowerCase(title + author.getKey());
    }

    public static String getKey(String title, Author author) {
        return toLowerCase(title + author.getKey());
    }
}