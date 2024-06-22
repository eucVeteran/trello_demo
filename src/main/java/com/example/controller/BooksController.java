package com.example.controller;

import com.example.model.Books;
import com.example.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Azizbek Toshpulatov
 */
@RestController
public class BooksController {
    @Autowired
    BooksService booksService;

    @GetMapping("/book")
    private List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/book/{bookID}")
    private Books getBooks(@PathVariable("bookID") int bookID)
    {
        return booksService.getBooksById(bookID);
    }

    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/book/{bookID}")
    private void deleteBook(@PathVariable("bookID") int bookID)
    {
        booksService.delete(bookID);
    }

    //creating post mapping that post the book detail in the database
    @PostMapping("/books")
    private int saveBook(@RequestBody Books books)
    {
        booksService.saveOrUpdate(books);
        return books.getBookID();
    }
    //creating put mapping that updates the book detail
    @PutMapping("/books")
    private Books update(@RequestBody Books books)
    {
        booksService.saveOrUpdate(books);
        return books;
    }
}
