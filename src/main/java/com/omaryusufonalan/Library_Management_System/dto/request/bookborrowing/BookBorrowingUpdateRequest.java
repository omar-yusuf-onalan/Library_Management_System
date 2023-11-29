package com.omaryusufonalan.Library_Management_System.dto.request.bookborrowing;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateRequest {
    @NotNull(message = "Book borrowing ID cannot be null")
    @NotEmpty(message = "Book borrowing ID cannot be empty")
    private int id;

    @NotNull(message = "Borrower name cannot be null")
    @NotEmpty(message = "Borrower name cannot be empty")
    private String borrowerName;

    @NotNull(message = "Borrowing date cannot be null")
    @NotEmpty(message = "Borrowing date cannot be empty")
    private LocalDate borrowingDate;

    @NotNull(message = "Return date cannot be null")
    @NotEmpty(message = "Return date cannot be empty")
    private LocalDate returnDate;

    @NotNull(message = "Book cannot be null")
    @NotEmpty(message = "Book cannot be empty")
    private Book book;
}
