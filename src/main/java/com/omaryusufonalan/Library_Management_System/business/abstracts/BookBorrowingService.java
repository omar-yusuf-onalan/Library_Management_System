package com.omaryusufonalan.Library_Management_System.business.abstracts;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.BookBorrowing;
import org.springframework.data.domain.Page;

public interface BookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
    BookBorrowing update(BookBorrowing bookBorrowing);
    Boolean delete(int id);
}
