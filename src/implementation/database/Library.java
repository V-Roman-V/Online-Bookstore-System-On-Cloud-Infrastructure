package implementation.database;

import implementation.database.entity.*;

import abstraction.database.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing Library which stores books
 */
public final class Library implements DataBaseInterface {

    /** Singleton object */
    private static Library instance;

    /** Hashmaps which stores databases */
    static HashMap<String, Book> book_table;
    static HashMap<String, Author> author_table;
    static HashMap<String, Reader> reader_table;
    static HashMap<String, Genre> genre_table;
    static HashMap<String, Order> current_order_table;
    static HashMap<String, Order> archived_order_table;

    /** Creates a library */
    private Library() {
        book_table = new HashMap<>();
        author_table = new HashMap<>();
        reader_table = new HashMap<>();
        genre_table = new HashMap<>();
        current_order_table = new HashMap<>();
        archived_order_table = new HashMap<>();
    }

    /**
     * An alternative to the constructor and is the access point to an instance of
     * Library class.
     */
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
            fill_up_my_vaults_like_administrator();
        }
        return instance;
    }

    @Override
    public ArrayList<Book> getBooksByAuthor(Author author) {
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getAuthor() == author).toList());
    }

    @Override
    public ArrayList<Book> getBooksByGenre(Genre genre) {
        return new ArrayList<>(book_table.values().stream().filter(book -> book.getGenre() == genre).toList());
    }

    @Override
    public ArrayList<Book> getBooksByFirstLetter(Character letter) {
        return new ArrayList<>(book_table.values().stream()
                .filter(book -> book.getTitle().toLowerCase().charAt(0) == letter).toList());
    }

    @Override
    public ArrayList<Genre> getListOfGenres() {
        return new ArrayList<>(genre_table.values());
    }

    @Override
    public ArrayList<Author> getListOfAuthors() {
        return new ArrayList<>(author_table.values());
    }

    @Override
    public ArrayList<Book> getListOfBooks() {
        return new ArrayList<>(book_table.values());
    }

    @Override
    public Boolean getIsBookAvailable(Book book) {
        return current_order_table.values().stream().anyMatch(order -> order.getBook() == book);
    }

    /**
     * This class is access point for creating, modifying, and deleting a Book.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    protected class BookForm {
        public static Book getInstance(String book_title, Author author) {
            String _book_key = Book.getKey(book_title, author);
            if (null == book_table.get(_book_key))
                book_table.put(_book_key, new Book(book_title, author));

            return book_table.get(_book_key);
        }

        public static Book getInstance(String book_title, String first_name, String last_name) {
            Author _author = Library.AuthorForm.getInstance(first_name, last_name);
            return Library.BookForm.getInstance(book_title, _author);
        }

        public static void delete(String book_title, Author author) {
            String _book_key = Book.getKey(book_title, author);
            book_table.remove(_book_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting an Author.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    protected class AuthorForm {
        public static Author getInstance(String first_name, String last_name) {
            String _author_key = Author.getKey(first_name, last_name);
            if (null == author_table.get(_author_key))
                author_table.put(_author_key, new Author(first_name, last_name));

            return author_table.get(_author_key);
        }

        public static void delete(String first_name, String last_name) {
            String _author_key = Author.getKey(first_name, last_name);
            author_table.remove(_author_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting a Genre.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    protected class GenreForm {
        public static Genre getInstance(String name) {
            String _genre_key = Genre.getKey(name);
            if (null == genre_table.get(_genre_key))
                genre_table.put(_genre_key, new Genre(name));

            return genre_table.get(_genre_key);
        }

        public static void delete(String name) {
            String _genre_key = Genre.getKey(name);
            genre_table.remove(_genre_key);
        }
    }

    /**
     * This class is access point for creating, modifying, and deleting a Reader.
     * Prevents duplication.
     * 
     * TODO configuring administrator-only access rights
     */
    protected class ReaderForm {
        public static Reader getInstance(String first_name, String last_name) {
            String _reader_key = Reader.getKey(first_name, last_name);
            if (null == reader_table.get(_reader_key))
                reader_table.put(_reader_key, new Reader(first_name, last_name));

            return reader_table.get(_reader_key);
        }

        public static void delete(String first_name, String last_name) {
            String _reader_key = Reader.getKey(first_name, last_name);
            reader_table.remove(_reader_key);
        }
    }

    @Override
    public Book getBookByID(int id) {
        for (Book book : book_table.values())
            if (book.getID() == id)
                return book;
        return null;
    }

    @Override
    public Author getAuthorByFullName(String fullname) {
        for (Author author : author_table.values())
            if (author.getFullName().equalsIgnoreCase(fullname))
                return author;
        return null;
    }

    @Override
    public Genre getGenreByName(String name) {
        for (Genre genre : genre_table.values())
            if (genre.getName().equalsIgnoreCase(name))
                return genre;
        return null;
    }

    @Override
    public Order getCurrentBookOrder(Book book) {
        for (Order order : current_order_table.values())
            if (order.getBook() == book)
                return order;
        return null;
    }

    @Override
    public void reqReserveBook(Book book, String first_name, String last_name) {
        Reader reader = new Reader(first_name, last_name);
        reader_table.put(reader.getKey(), reader);// TODO
        Order order = new Order(book, reader);
        current_order_table.put(order.getKey(), order);
    }

    @Override
    public void reqReleaseBook(Book book) {
        Order order = getCurrentBookOrder(book);
        if (order == null)
            return;
        current_order_table.remove(order.getKey());
        order.date_return(new Date(System.currentTimeMillis()));
        archived_order_table.put(order.getKey(), order);
    }

    private static void fill_up_my_vaults_like_administrator() {

        Genre DETECTIVE = GenreForm.getInstance("Detective").description("like sherlok Pomps");
        Genre HORROR = GenreForm.getInstance("Horror").description("you will scream");
        Genre COMEDY = GenreForm.getInstance("Comedy").description("you will ha-ha-ah ygar))! ");
        Genre FICTION = GenreForm.getInstance("Fiction").description("is about deep space galaxy or far future");
        Genre THRILLER = GenreForm.getInstance("Thriller").description("pif-pav russian weekdays");
        Genre EROTIC = GenreForm.getInstance("Erotic").description("Attention, 16+ only!");
        Genre DRAMA = GenreForm.getInstance("Drama").description("It is not about farcry");
        Genre PSYCHOLOGICAL = GenreForm.getInstance("Psychological").description("aaaaaa");
        Genre ROMANCE = GenreForm.getInstance("Romance").description("aaaaaa");
        Genre ECCHI = GenreForm.getInstance("Ecchi").description("Attention, 18+ only!");

        BookForm.getInstance("Max", "Alexsander", "Pushkin").price(22.2).genre(DETECTIVE);
        BookForm.getInstance("Nastya","Lenin","Ulianov").genre(HORROR).price(12.22).note("...");
        BookForm.getInstance("Rust", "Alexsander", "Krug").price(85.4).genre(FICTION);
        BookForm.getInstance("Bong", "Stephen", "King").price(24.4).genre(THRILLER);
        BookForm.getInstance("ProbStat", "Sergey", "Gorodetskiy").price(9999.9).genre(EROTIC);
        BookForm.getInstance("Center", "Norman", "Osborn").price(85.4).genre(HORROR);
        BookForm.getInstance("My House of Horrors (LN)", "Okamura", "Tadashi").price(85.4).genre(HORROR);


        Author author1 = AuthorForm.getInstance("Thinker", "Cristiano");
        BookForm.getInstance("Ronaldo", author1).price(10.9).genre(COMEDY);

        Author author2 = AuthorForm.getInstance("Nikolay", "Tolstoy");
        BookForm.getInstance("Mumu", author2).price(120.1).genre(DRAMA);
        BookForm.getInstance("War and Peace", author2).price(309.8).genre(DRAMA);
        BookForm.getInstance("Anna Karenina", author2).price(238.0).genre(DRAMA);

        Author author3 = AuthorForm.getInstance("Andjey", "Sapkovskiy");
        BookForm.getInstance("The Witcher: Last wish", author3).price(873.2).genre(FICTION);
        BookForm.getInstance("The Witcher: The blood of elves", author3).price(2109.2).genre(FICTION);
        BookForm.getInstance("The Witcher: Owner of lake", author3).price(479.2).genre(FICTION);
        BookForm.getInstance("THE Witcher: Baptism of fire", author3).price(396.9).genre(FICTION);

        Author author4 = AuthorForm.getInstance("Natalya", "Nesterova");
        BookForm.getInstance("Find a cat", author4).price(182.2).genre(ROMANCE);
        BookForm.getInstance("Polina Sergeevna", author4).price(164.0).genre(ROMANCE);
        BookForm.getInstance("Make it louder", author4).price(410.0).genre(ROMANCE);
        BookForm.getInstance("You do not hear me", author4).price(182.4).genre(ROMANCE);

        Author author5 = AuthorForm.getInstance("Ashira", "Haan");
        BookForm.getInstance("Love without dress-code", author5).price(340.8).genre(EROTIC);
        BookForm.getInstance("Sex only", author5).price(400.2).genre(EROTIC);
        BookForm.getInstance("Lover of your husband", author5).price(538.1).genre(EROTIC);
        BookForm.getInstance("The ninth neighbor", author5).price(380.7).genre(EROTIC);

        BookForm.getInstance("The Beginning After the End", "Kiguasa", "Shougo").price(85.0).genre(FICTION);
        BookForm.getInstance("Re:Zero - Starting Life in Another World","NAGATSUKI", "Tappei").price(20.9).genre(ROMANCE);
        BookForm.getInstance("No Game No Life","KAMIYA", "Yuu").price(7.5).genre(ECCHI);
        BookForm.getInstance("I'm A Spider, So What?","BABA", "Okina").price(61.5).genre(PSYCHOLOGICAL);
        BookForm.getInstance("Classroom of the Elite","KINUGASA", "Shougo").price(18.9).genre(DETECTIVE);
        BookForm.getInstance("Jobless Reincarnation: I Will Seriously Try If I Go to Another World","Rifujin", "Magonote").price(37.6).genre(COMEDY);
        BookForm.getInstance("How to forget C++, for dummies","Rifujin", "Magonote").price(0.1).genre(PSYCHOLOGICAL);
        BookForm.getInstance("Spice and Wolf","Isuna", "Hasekura").price(56.7).genre(DRAMA);
        BookForm.getInstance("Danganronpa/Zero","Kazutaka", "Kodaka").price(3.3).genre(DETECTIVE);
        BookForm.getInstance("Knights of the Forty Islands","Sergey", "Lukyanenko").price(19.9).genre(FICTION);
        BookForm.getInstance("The Stormlight Archive","Brandon", "Sanderson").price(9.25).genre(FICTION);
        BookForm.getInstance("Mistborn Trilogy","Brandon", "Sanderson").price(8.66).genre(FICTION);
        BookForm.getInstance("Violet Evergarden","AKATSUKI", "Kana").price(6.7).genre(ROMANCE);
    }
}