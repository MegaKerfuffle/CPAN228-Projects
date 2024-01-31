package com.example.assignment1.models;

import com.example.assignment1.repositories.BookRepository;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    /**
     * Note that the ID given in this CTOR will be
     * overriden, if adding the book to the repo.
     */
    public Book(int id) {
        this.id = id;
    }

    @Builder
    public Book(String title, String author, double price) {
        // This setup for IDs probably wouldn't be good for
        // a real system, but it works for this assignment.
        // Might want to use the book's ISBN or something IRL.
        this.id = BookRepository.getLastId();
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
