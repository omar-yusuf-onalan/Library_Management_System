package com.omaryusufonalan.Library_Management_System.business.abstracts;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    Book save(Book book);
    Book get(int id);
    Page<Book> cursor(int page, int pageSize);
    Book update(Book book);
    Boolean delete(int id);
    void getCategories(int bookId);
    void setCategories(int bookId, int categoryId);
}
