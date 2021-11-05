package implementation.database.entity;

import abstraction.database.entity.EntityInterface;

/**
 * A class representing Implementation.database.object.ReadOnlyBook
 */
public class ReadOnlyBook implements EntityInterface {
    /**
     * Coefficient which makes id unique for each ReadOnlyBook
     */
    private static int ID_coefficient;

    /**
     * Property of ReadOnlyBooks
     * 
     * final means key fuild
     */
    protected final String title;
    protected final ReadOnlyAuthor author;

    protected String note;
    protected ReadOnlyGenre genre;
    protected Double price;

    protected final int ID;

    /**
     * Creates a ReadOnlyBook
     */
    public ReadOnlyBook(String title, ReadOnlyAuthor author) {
        ID = ++ID_coefficient;
        this.title = title;
        this.author = author;

        this.genre = null;
        this.price = null;
        this.note = null;
    }


    public ReadOnlyAuthor getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public ReadOnlyGenre getGenre() {
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

    public static String getKey(String title, ReadOnlyAuthor author) {
        return title + author.getKey();
    }
}