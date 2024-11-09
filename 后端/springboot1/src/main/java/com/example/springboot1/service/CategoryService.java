package com.example.springboot1.service;

import com.example.springboot1.pojo.Category;

import java.util.List;

public interface CategoryService {
    boolean add(Category category);

    List<Category> list();

    Category detail(Integer id);

    void update(Category category);

    void delete(Integer id);
}
