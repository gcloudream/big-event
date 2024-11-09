package com.example.springboot1.service.impl;

import com.example.springboot1.mapper.CategoryMapper;
import com.example.springboot1.pojo.Category;
import com.example.springboot1.service.CategoryService;
import com.example.springboot1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public boolean add(Category category) {
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();
        Integer integer = categoryMapper.checkCategoryName(categoryName, categoryAlias);
        if(integer!=null){
            return false;
        }
        Map<String, Object> stringObjectMap=ThreadLocalUtil.get();
        category.setCreateUser((Integer) stringObjectMap.get("id"));
        categoryMapper.add(category);
        return true;
    }

    @Override
    public List<Category> list() {
//        Map<String, Object> stringObjectMap=ThreadLocalUtil.get();
//        Integer id = (Integer) stringObjectMap.get("id");
        List<Category> list = categoryMapper.list();
        return list;
    }

    @Override
    public Category detail(Integer id) {
        Category detail = categoryMapper.detail(id);
        return detail;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
