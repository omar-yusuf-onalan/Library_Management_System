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
public class CategorySaveRequest {
    @NotNull(message = "Category name cannot be null")
    @NotEmpty(message = "Category name cannot be empty")
    private String name;

    @NotNull(message = "Category description cannot be null")
    @NotEmpty(message = "Category description cannot be empty")
    private String description;

    private List<Book> bookList;
}
