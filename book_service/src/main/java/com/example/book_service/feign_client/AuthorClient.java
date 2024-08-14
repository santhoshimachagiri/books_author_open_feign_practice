package com.example.book_service.feign_client;

import com.example.book_service.dto.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "author-service", url = "http://localhost:8081/api")
public interface AuthorClient {
    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id);
}
