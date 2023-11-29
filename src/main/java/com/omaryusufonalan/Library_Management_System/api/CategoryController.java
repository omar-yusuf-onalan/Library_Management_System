package com.omaryusufonalan.Library_Management_System.api;

import com.omaryusufonalan.Library_Management_System.business.abstracts.CategoryService;
import com.omaryusufonalan.Library_Management_System.core.config.modelMapper.ModelMapperService;
import com.omaryusufonalan.Library_Management_System.core.result.Result;
import com.omaryusufonalan.Library_Management_System.core.result.ResultData;
import com.omaryusufonalan.Library_Management_System.core.result.ResultDataGenerator;
import com.omaryusufonalan.Library_Management_System.dto.request.category.CategorySaveRequest;
import com.omaryusufonalan.Library_Management_System.dto.request.category.CategoryUpdateRequest;
import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.category.CategoryResponse;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapperService modelMapperService;

    public CategoryController(CategoryService categoryService, ModelMapperService modelMapperService) {
        this.categoryService = categoryService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapperService.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);

        CategoryResponse categoryResponse = this.modelMapperService.forResponse().map(saveCategory, CategoryResponse.class);
        return ResultDataGenerator.generateCreatedFor(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id) {
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this.modelMapperService.forResponse().map(category, CategoryResponse.class);

        return ResultDataGenerator.generateOkFor(categoryResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "page", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Category> categoryPage = this.categoryService.cursor(page, pageSize);

        Page<CategoryResponse> categoryResponsePage = categoryPage.map(category -> this.modelMapperService.forRequest().map(category, CategoryResponse.class));

        return ResultDataGenerator.generateCursorFor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapperService.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);

        CategoryResponse categoryResponse = this.modelMapperService.forResponse().map(updateCategory, CategoryResponse.class);
        return ResultDataGenerator.generateOkFor(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);
        return ResultDataGenerator.generateOk();
    }
}

