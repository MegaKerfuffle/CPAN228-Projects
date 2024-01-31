package com.example.assignment1.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.assignment1.models.Book;

// Annotation not needed, since we're using statics.
//@Repository
public class BookRepository {
    private static List<Book> contents = new ArrayList<>();  
    private static int lastId = 0;

    static {
        // Add some sample books. Can't directly add to the 
        // list, as the IDs wouldn't be tracked correctly.
        addBook(Book.builder()
            .title("Hamlet")
            .author("William Shakespeare")
            .price(33.99)
            .build());
        
        addBook(Book.builder()
            .title("King Lear")
            .author("William Shakespeare")
            .price(18.99)
            .build());

        addBook(Book.builder()
            .title("Macbeth")
            .author("Billy Shakes")
            .price(24.99)
            .build());

        addBook(Book.builder()
            .title("A Promised Land")
            .author("Barack Obama")
            .price(39.99)
            .build());
    }

    /**
     * Retrieves the contents of the repository
     */
    public static List<Book> getContents() { return contents; }

    /**
     * Retrieves the ID of the book most recently
     * added to the repository.
     */
    public static int getLastId() { return lastId; }

    /**
     * Adds a book to the repository. The book's ID
     * is set according to the last used ID, plus one.
     */
    public static void addBook(Book book) {
        // Not doing any validation here - that probably isn't ideal.
        // A better implementation would do all the validation here
        // instead, I think, rather than in the service.
        book.setId(lastId++);
        contents.add(book);
    }

    // Private CTOR to block attempts to instantiate
    // C# static classes do this better :)
    private BookRepository() { super(); }
}
