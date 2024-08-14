package com.example.book_service.controller;

import com.example.book_service.domain.Book;
import com.example.book_service.dto.Author;
import com.example.book_service.dto.BookDto;
import com.example.book_service.exceptions.AuthorNotFoundException;
import com.example.book_service.exceptions.BookNotFoundException;
import com.example.book_service.feign_client.AuthorClient;
import com.example.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable Long id) {

        Book book = bookRepository.findById(id);

        if (book != null) {
            // Convert the Book entity to a BookDTO and return with a 200 OK status
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        // Convert the Book entity to a BookDTO
        return convertToDTO(book);
    }

    private BookDto convertToDTO(Book book) {
        // Fetch the author details from the AuthorClient (assuming AuthorClient provides such functionality)
        Author author = authorClient.getAuthorById(book.getAuthorId());
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + book.getAuthorId());
        }

        // Create and return a BookDTO
        return new BookDto(book.getId(), book.getTitle(), author);
    }
}
