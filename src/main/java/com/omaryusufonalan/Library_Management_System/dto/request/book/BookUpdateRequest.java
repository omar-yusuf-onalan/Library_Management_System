package com.omaryusufonalan.Library_Management_System.dto.request.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Positive
    private int id;

    @NotNull(message = "Book name cannot be null")
    @NotEmpty(message = "Book name cannot be empty")
    private String name;

    @NotNull(message = "Publication year cannot be null")
    @NotEmpty(message = "Publication year cannot be empty")
    private int publicationYear;

    @NotNull(message = "Stock cannot be null")
    @NotEmpty(message = "Stock cannot be empty")
    private int stock;

    @NotNull(message = "Author ID cannot be null")
    @NotEmpty(message = "Author ID cannot be empty")
    private int authorId;

    @NotNull(message = "Publisher ID cannot be null")
    @NotEmpty(message = "Publisher ID cannot be empty")
    private int publisherId;

    @NotNull(message = "Category IDs cannot be null")
    @NotEmpty(message = "Category IDs cannot be empty")
    private List<Integer> categoryIds;
}
