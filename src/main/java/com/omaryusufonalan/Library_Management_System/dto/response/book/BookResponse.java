package com.omaryusufonalan.Library_Management_System.dto.response.book;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import com.omaryusufonalan.Library_Management_System.entity.Publisher;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String name;
    private int publicationYear;
    private int stock;
    private Author author;
    private Publisher publisher;
    private List<Category> categoryList;
}
