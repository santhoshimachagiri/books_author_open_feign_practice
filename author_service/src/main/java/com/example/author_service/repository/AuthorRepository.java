package com.example.author_service.repository;

import com.example.author_service.domain.Author;
import com.example.author_service.exceptions.AuthorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author(1L, "George Orwell"));
        authors.add(new Author(2L, "J.K. Rowling"));
        authors.add(new Author(3L, "Harper Lee"));
        authors.add(new Author(4L, "J.R.R. Tolkien"));
        authors.add(new Author(5L, "Jane Austen"));
    }

    public List<Author> findAll() {
        log.debug("Getting all patients");
        return List.copyOf(authors);
    }

    public Author findById(Long id) {
        Optional<Author> author = authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        return author.orElseThrow(() -> new AuthorNotFoundException("Author not found, id: " + id));
    }
}
