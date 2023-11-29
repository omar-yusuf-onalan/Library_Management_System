package com.omaryusufonalan.Library_Management_System.business.concretes;

import com.omaryusufonalan.Library_Management_System.business.abstracts.BookBorrowingService;
import com.omaryusufonalan.Library_Management_System.core.exception.NotFoundException;
import com.omaryusufonalan.Library_Management_System.core.result.ResultMessage;
import com.omaryusufonalan.Library_Management_System.dao.BookBorrowingRepo;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements BookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public Boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);

        return true;
    }
}

