package com.omaryusufonalan.Library_Management_System.api;

import com.omaryusufonalan.Library_Management_System.business.abstracts.BookBorrowingService;
import com.omaryusufonalan.Library_Management_System.core.config.modelMapper.ModelMapperService;
import com.omaryusufonalan.Library_Management_System.core.result.Result;
import com.omaryusufonalan.Library_Management_System.core.result.ResultData;
import com.omaryusufonalan.Library_Management_System.core.result.ResultDataGenerator;
import com.omaryusufonalan.Library_Management_System.dto.request.bookborrowing.BookBorrowingSaveRequest;
import com.omaryusufonalan.Library_Management_System.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.bookborrowing.BookBorrowingResponse;
import com.omaryusufonalan.Library_Management_System.entity.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bookBorrowings")
public class BookBorrowingController {
    private final BookBorrowingService bookBorrowingService;
    private final ModelMapperService modelMapperService;

    public BookBorrowingController(BookBorrowingService bookBorrowingService, ModelMapperService modelMapperService) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing saveBookBorrowing = this.modelMapperService.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
        this.bookBorrowingService.save(saveBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapperService.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class);
        return ResultDataGenerator.generateCreatedFor(bookBorrowingResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = this.modelMapperService.forResponse().map(bookBorrowing, BookBorrowingResponse.class);

        return ResultDataGenerator.generateOkFor(bookBorrowingResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "page", required = false, defaultValue = "10") int pageSize
    ) {
        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page, pageSize);

        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage.map(bookBorrowing -> this.modelMapperService.forRequest().map(bookBorrowing, BookBorrowingResponse.class));

        return ResultDataGenerator.generateCursorFor(bookBorrowingResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing = this.modelMapperService.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrowing);

        BookBorrowingResponse bookBorrowingResponse = this.modelMapperService.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class);
        return ResultDataGenerator.generateOkFor(bookBorrowingResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);
        return ResultDataGenerator.generateOk();
    }
}

