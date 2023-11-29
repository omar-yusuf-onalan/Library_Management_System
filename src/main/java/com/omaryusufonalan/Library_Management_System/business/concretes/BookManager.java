package com.omaryusufonalan.Library_Management_System.business.concretes;

import com.omaryusufonalan.Library_Management_System.business.abstracts.BookService;
import com.omaryusufonalan.Library_Management_System.core.exception.NotFoundException;
import com.omaryusufonalan.Library_Management_System.core.result.ResultMessage;
import com.omaryusufonalan.Library_Management_System.dao.AuthorRepo;
import com.omaryusufonalan.Library_Management_System.dao.BookRepo;
import com.omaryusufonalan.Library_Management_System.dao.CategoryRepo;
import com.omaryusufonalan.Library_Management_System.dao.PublisherRepo;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookManager implements BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final PublisherRepo publisherRepo;
    private final CategoryRepo categoryRepo;

    public BookManager(BookRepo bookRepo, AuthorRepo authorRepo, PublisherRepo publisherRepo, CategoryRepo categoryRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.publisherRepo = publisherRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(int id) {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND));

        List<Integer> categoryIds = this.bookRepo.findByBookIdNative(id);
        List<Category> categoryList = new ArrayList<>();

        for (Integer catId : categoryIds) {
            categoryList.add(this.categoryRepo.findById(catId).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND)));
        }

        book.setCategoryList(categoryList);
        return book;
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public Boolean delete(int id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);

        return true;
    }

    @Override
    public void getCategories(int bookId) {
        this.bookRepo.findByBookIdNative(bookId);
    }

    @Override
    public void setCategories(int bookId, int categoryId) {
        this.bookRepo.insertIntoBooksToCategories(bookId, categoryId);
    }
}
