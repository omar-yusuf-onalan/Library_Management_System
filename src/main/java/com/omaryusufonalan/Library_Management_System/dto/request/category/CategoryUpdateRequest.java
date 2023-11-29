package com.omaryusufonalan.Library_Management_System.dto.request.category;

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
public class CategoryUpdateRequest {
    @NotNull(message = "Category ID cannot be null")
    @NotEmpty(message = "Category ID cannot be empty")
    private int id;

    @NotNull(message = "Category name cannot be null")
    @NotEmpty(message = "Category name cannot be empty")
    private String name;

    @NotNull(message = "Category description cannot be null")
    @NotEmpty(message = "Category description cannot be empty")
    private String description;

    @NotNull(message = "Author name cannot be null")
    @NotEmpty(message = "Author name cannot be empty")
    private List<Book> bookList;
}
