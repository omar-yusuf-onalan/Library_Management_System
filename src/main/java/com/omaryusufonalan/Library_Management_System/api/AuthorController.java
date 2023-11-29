package com.omaryusufonalan.Library_Management_System.api;

import com.omaryusufonalan.Library_Management_System.business.abstracts.AuthorService;
import com.omaryusufonalan.Library_Management_System.core.config.modelMapper.ModelMapperService;
import com.omaryusufonalan.Library_Management_System.core.result.Result;
import com.omaryusufonalan.Library_Management_System.core.result.ResultData;
import com.omaryusufonalan.Library_Management_System.core.result.ResultDataGenerator;
import com.omaryusufonalan.Library_Management_System.dto.request.author.AuthorSaveRequest;
import com.omaryusufonalan.Library_Management_System.dto.request.author.AuthorUpdateRequest;
import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.author.AuthorResponse;
import com.omaryusufonalan.Library_Management_System.entity.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final ModelMapperService modelMapperService;

    public AuthorController(AuthorService authorService, ModelMapperService modelMapperService) {
        this.authorService = authorService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapperService.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);

        AuthorResponse authorResponse = this.modelMapperService.forResponse().map(saveAuthor, AuthorResponse.class);
        return ResultDataGenerator.generateCreatedFor(authorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapperService.forResponse().map(author, AuthorResponse.class);

        return ResultDataGenerator.generateOkFor(authorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "page", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);

        Page<AuthorResponse> authorResponsePage = authorPage.map(author -> this.modelMapperService.forRequest().map(author, AuthorResponse.class));

        return ResultDataGenerator.generateCursorFor(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapperService.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);

        AuthorResponse authorResponse = this.modelMapperService.forResponse().map(updateAuthor, AuthorResponse.class);
        return ResultDataGenerator.generateOkFor(authorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultDataGenerator.generateOk();
    }
}
