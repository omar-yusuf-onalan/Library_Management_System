package com.omaryusufonalan.Library_Management_System.dto.response.bookborrowing;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Book book;
}
