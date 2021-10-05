public class Book {
    private String title;
    private int ID;
    private String genre;
    private String author;
    private String bookerName;
    private double price;
    private boolean booked;

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

    public String getGenre() {
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