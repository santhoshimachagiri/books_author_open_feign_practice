package com.example.book_service.dto;

public record BookDto(
        Long id,
        String title,
        Author author
) {
}
