package com.example.springboot1.service;

import com.example.springboot1.pojo.Article;
import com.example.springboot1.pojo.PageBean;
import com.example.springboot1.pojo.Result;

public interface ArticleService{
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article detail(Integer id);

    void update(Article article);

    void delete(Integer id);
}
