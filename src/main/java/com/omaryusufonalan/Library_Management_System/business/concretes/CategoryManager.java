package com.omaryusufonalan.Library_Management_System.business.concretes;

import com.omaryusufonalan.Library_Management_System.business.abstracts.CategoryService;
import com.omaryusufonalan.Library_Management_System.core.exception.NotFoundException;
import com.omaryusufonalan.Library_Management_System.core.result.ResultMessage;
import com.omaryusufonalan.Library_Management_System.dao.CategoryRepo;
import com.omaryusufonalan.Library_Management_System.entity.Book;
import com.omaryusufonalan.Library_Management_System.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public Boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);

        return true;
    }
}
