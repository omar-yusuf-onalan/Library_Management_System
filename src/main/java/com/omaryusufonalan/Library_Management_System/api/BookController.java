package com.omaryusufonalan.Library_Management_System.api;

import com.omaryusufonalan.Library_Management_System.business.abstracts.AuthorService;
import com.omaryusufonalan.Library_Management_System.business.abstracts.BookService;
import com.omaryusufonalan.Library_Management_System.business.abstracts.CategoryService;
import com.omaryusufonalan.Library_Management_System.business.abstracts.PublisherService;
import com.omaryusufonalan.Library_Management_System.core.config.modelMapper.ModelMapperService;
import com.omaryusufonalan.Library_Management_System.core.result.Result;
import com.omaryusufonalan.Library_Management_System.core.result.ResultData;
import com.omaryusufonalan.Library_Management_System.core.result.ResultDataGenerator;
import com.omaryusufonalan.Library_Management_System.dto.request.book.BookSaveRequest;
import com.omaryusufonalan.Library_Management_System.dto.request.book.BookUpdateRequest;
import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.book.BookResponse;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final ModelMapperService modelMapperService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService, ModelMapperService modelMapperService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapperService.forRequest().map(bookSaveRequest, Book.class);
        this.bookService.save(saveBook);

        List<Integer> categoryIds = bookSaveRequest.getCategoryIds();
        List<Category> categoryList = new ArrayList<>();

        for (int catId : categoryIds) {
            this.bookService.setCategories(saveBook.getId(),catId);
            categoryList.add(this.categoryService.get(catId));
        }

        saveBook.setAuthor(this.authorService.get(bookSaveRequest.getAuthorId()));
        saveBook.setPublisher(this.publisherService.get(bookSaveRequest.getPublisherId()));
        saveBook.setCategoryList(categoryList);

        BookResponse bookResponse = this.modelMapperService.forResponse().map(saveBook, BookResponse.class);
        return ResultDataGenerator.generateCreatedFor(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapperService.forResponse().map(book, BookResponse.class);

        return ResultDataGenerator.generateOkFor(bookResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "page", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Book> bookPage = this.bookService.cursor(page, pageSize);

        Page<BookResponse> bookResponsePage = bookPage.map(book -> this.modelMapperService.forRequest().map(book, BookResponse.class));

        return ResultDataGenerator.generateCursorFor(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapperService.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);

        List<Integer> categoryIds = bookUpdateRequest.getCategoryIds();
        List<Category> categoryList = new ArrayList<>();

        for (int catId : categoryIds) {
            this.bookService.setCategories(updateBook.getId(),catId);
            categoryList.add(this.categoryService.get(catId));
        }

        updateBook.setAuthor(this.authorService.get(bookUpdateRequest.getAuthorId()));
        updateBook.setPublisher(this.publisherService.get(bookUpdateRequest.getPublisherId()));
        updateBook.setCategoryList(categoryList);

        BookResponse bookResponse = this.modelMapperService.forResponse().map(updateBook, BookResponse.class);
        return ResultDataGenerator.generateOkFor(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultDataGenerator.generateOk();
    }

}
