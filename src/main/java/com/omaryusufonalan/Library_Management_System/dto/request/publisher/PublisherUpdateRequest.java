package com.omaryusufonalan.Library_Management_System.dto.request.publisher;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @NotNull(message = "Publisher ID cannot be null")
    @NotEmpty(message = "Publisher ID cannot be empty")
    private int id;
    @NotNull(message = "Publisher name cannot be null")
    @NotEmpty(message = "Publisher name cannot be empty")
    private String name;

    @NotNull(message = "Publisher description cannot be null")
    @NotEmpty(message = "Publisher description cannot be empty")
    private String description;

    @NotNull(message = "Book list cannot be null")
    @NotEmpty(message = "Book list cannot be empty")
    private List<Book> bookList;
}
