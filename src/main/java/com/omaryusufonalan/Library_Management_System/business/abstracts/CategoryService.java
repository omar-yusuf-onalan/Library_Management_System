package com.omaryusufonalan.Library_Management_System.business.abstracts;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Category save(Category category);
    Category get(int id);
    Page<Category> cursor(int page, int pageSize);
    Category update(Category category);
    Boolean delete(int id);
}
