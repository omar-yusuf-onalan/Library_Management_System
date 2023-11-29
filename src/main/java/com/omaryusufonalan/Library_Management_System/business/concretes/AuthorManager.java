package com.omaryusufonalan.Library_Management_System.business.concretes;

import com.omaryusufonalan.Library_Management_System.business.abstracts.AuthorService;
import com.omaryusufonalan.Library_Management_System.core.exception.NotFoundException;
import com.omaryusufonalan.Library_Management_System.core.result.ResultMessage;
import com.omaryusufonalan.Library_Management_System.dao.AuthorRepo;
import com.omaryusufonalan.Library_Management_System.dao.BookRepo;
import com.omaryusufonalan.Library_Management_System.entity.Author;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements AuthorService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public AuthorManager(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        Author author = this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND));
        List<Book> bookList = this.bookRepo.findByAuthorIdNative(author.getId());
        author.setBookList(bookList);
        return author;
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepo.findAll(pageable);
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public Boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);

        return true;
    }
}
