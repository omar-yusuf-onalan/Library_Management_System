package com.omaryusufonalan.Library_Management_System.dto.request.book;

import com.omaryusufonalan.Library_Management_System.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {
    @NotNull(message = "Book name cannot be null")
    @NotEmpty(message = "Book name cannot be empty")
    private String name;

    @NotNull(message = "Publication year cannot be null")
    private int publicationYear;

    @NotNull(message = "Stock cannot be null")
    private int stock;

    @NotNull(message = "Author ID cannot be null")
    private int authorId;

    @NotNull(message = "Publisher ID cannot be null")
    private int publisherId;

    private List<Integer> categoryIds;
}
