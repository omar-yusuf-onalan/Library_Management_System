package com.omaryusufonalan.Library_Management_System.business.abstracts;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import org.springframework.data.domain.Page;

public interface AuthorService {
    Author save(Author author);
    Author get(int id);
    Page<Author> cursor(int page, int pageSize);
    Author update(Author author);
    Boolean delete(int id);
}
