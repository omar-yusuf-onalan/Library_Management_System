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
public class BookBorrowingSaveRequest {
    @NotNull(message = "Borrower name cannot be null")
    @NotEmpty(message = "Borrower name cannot be empty")
    private String borrowerName;

    @NotNull(message = "Borrowing date cannot be null")
    private LocalDate borrowingDate;

    @NotNull(message = "Return date cannot be null")
    private LocalDate returnDate;

    @NotNull(message = "Book cannot be null")
    private Book book;
}
