/**
 * A class representing Book
 */
public class Book {
    /**
     * Coefficient which makes id unique for each book
     */
    private static int ID_coefficient;

    /**
     * Properties of books
     */
    private final String title;
    private final String author;
    private final Genre genre;
    private final double price;
    private final int ID;
    private boolean booked;
    private String bookerName;

    /**
     * Creates a book
     */
    public Book(String title, Genre genre, String author, double price) {
        ID = ++ID_coefficient;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
    }

    /**
     * @return was the book actually booked
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Makes book free
     */
    public void releaseBook() {
        this.booked = false;
    }

    /**
     * @param bookerName Makes book booked and set bookerName which converges with
     *                   parameter
     */
    public void setBooker(String bookerName) {
        this.bookerName = bookerName;
        this.booked = true;
    }

    /**
     * @return the title of book
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return id of the book
     */
    public int getID() {
        return ID;
    }

    /**
     * @return author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return genre of the book
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @return name of the booker
     */
    public String getBookerName() {
        return bookerName;
    }

    /**
     * @return price of the book
     */
    public double getPrice() {
        return price;
    }
}