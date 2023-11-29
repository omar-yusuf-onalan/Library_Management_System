package com.omaryusufonalan.Library_Management_System.dto.response.author;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private String name;
    private LocalDate birthdate;
    private String country;
    private List<Book> bookList;
}
