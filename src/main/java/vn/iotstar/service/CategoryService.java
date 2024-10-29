package vn.iotstar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.Category;

public interface CategoryService {
    Page<Category> getAllCategories(Pageable pageable);

    Category getCategoryById(Long id);

    <S extends Category> S save(S entity);

    void deleteCategory(Long id);

    List<Category> findAll();
}