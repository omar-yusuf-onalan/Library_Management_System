package com.omaryusufonalan.Library_Management_System.dto.response.category;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String name;
    private String description;
    private List<Book> bookList;
}
