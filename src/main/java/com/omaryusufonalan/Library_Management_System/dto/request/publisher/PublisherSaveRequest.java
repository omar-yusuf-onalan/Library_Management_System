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
public class PublisherSaveRequest {
    @NotNull(message = "Publisher name cannot be null")
    @NotEmpty(message = "Publisher name cannot be empty")
    private String name;

    @NotNull(message = "Publisher description cannot be null")
    @NotEmpty(message = "Publisher description cannot be empty")
    private String description;

    private List<Book> bookList;
}
