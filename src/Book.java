public class Book {
    private static int ID_coefficient;
    private String title;
    private int ID;
    private Genre genre;
    private String author;
    private String bookerName;
    private double price;
    private boolean booked;

    public Book(String title, Genre genre, String author, double price) {
        ID = ++ID_coefficient;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
    }

    public void releaseBook(){
        this.booked = false;
    }

    public void setBooker(String bookerName) {
        this.bookerName = bookerName;
        this.booked = true;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getBookerName() {
        return bookerName;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }
}