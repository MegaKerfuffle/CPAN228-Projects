package com.example.assignment1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.assignment1.models.Book;
import com.example.assignment1.repositories.BookRepository;

@Service
public class BookService {
    private static final double MAX_PRICE = 100;
    
    private String pendingStatus = "";
    private String pendingDescription = "";

    /**
     * Retrieves all entries from the Book Repository.
     */
    public List<Book> getBooks() {
        return BookRepository.getContents();
    }

    /**
     * Check if the service has a status to report.
     * @return `true` if pending status has a value 
     */
    public boolean hasPendingStatus() {
        return pendingStatus.length() > 0;
    }

    /**
     * Get and clear the current status.
     */
    public String getPendingStatus() {
        String status = pendingStatus;
        pendingStatus = "";
        return status;
    }

    /**
     * Get and clear the current description.
     */
    public String getPendingDescription() {
        String desc = pendingDescription;
        pendingDescription = "";
        return desc; 
    }

    /**
     * Attempt to add a book to the Book Repository.
     * @return true if successful, false otherwise
     */
    public boolean tryAddBook(Book book) {
        // Failure state - invalid ID (can't happen in normal usage as is)
        if (book.getId() <= BookRepository.getLastId()) {
            pendingStatus = "failure";
            pendingDescription = "Book ID is already used!";
            return false;
        } 
        // Failure state - exceeds max price
        if (book.getPrice() > MAX_PRICE) 
        {
            pendingStatus = "failure";
            pendingDescription = "Book price is too high!";
            return false;
        }

        // Set success state & description, to be read later
        pendingStatus = "success";
        pendingDescription = "Book added successfully!";

        // Add the book and return
        BookRepository.addBook(book);
        return true;
    }
}
