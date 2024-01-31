package com.example.assignment1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.assignment1.models.Book;
import com.example.assignment1.repositories.BookRepository;
import com.example.assignment1.services.BookService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    BookService bookService;

    @Value("${library.name}")
    public String name;

    /**
     * Retrieves the home page template.
     */
    @GetMapping(value = {"/", "/home"})
    public String getHome(Model model) {
        return getTemplate("home", model);
    }

    /**
     * Retrieves the catalogue page template.
     */
    @GetMapping("/catalogue")
    public String getCatalogue(Model model) {
        // Add books as an attribute
        model.addAttribute("books", bookService.getBooks());

        // Moved alert handling to here, since I added the whole
        // redirect thing in the post method. Alert statuses and
        // descriptions are set in the service, and retrieved 
        // here if they exist.
        if (bookService.hasPendingStatus()) {
            String status = bookService.getPendingStatus(); 
            String desc = bookService.getPendingDescription();
            if (status != null && !status.isBlank()) {
                model.addAttribute(status, desc);
            } 
        }
        return getTemplate("catalogue", model);
    }
    
    /**
     * Retrieves the add book page template.
     */
    @GetMapping("/add-book")
    public String getBookForm(Model model) {
        model.addAttribute("newBook", new Book(BookRepository.getLastId()));
        return getTemplate("book-form", model);
    }
    
    /**
     * Handle POST requests for adding new books.
     */
    @PostMapping("/post-book")
    public String postBook(Model model, @ModelAttribute Book book) {
        // There's probably a better solution here (perhaps just not
        // checking the ID in the first place), but this works.
        book.setId(BookRepository.getLastId() + 1);

        // Attempt to add the book to the repo.
        bookService.tryAddBook(book);

        // NOTE: See comment above (in `getCatalogue()` for why alerts aren't here.

        // Redirect to avoid keeping the URL as `/post-book`
        return getTemplate("redirect:/catalogue", model);
    }
    
    /**
     * Retrieves the page template given by name, and
     * injects the 'libraryName' attribute.
     */
    private String getTemplate(String templateName, Model model) {
        model.addAttribute("libraryName", name);
        return templateName;
    }
}