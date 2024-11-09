package com.example.springboot1.service.impl;

import com.example.springboot1.mapper.ArticleMapper;
import com.example.springboot1.pojo.Article;
import com.example.springboot1.pojo.PageBean;
import com.example.springboot1.pojo.Result;
import com.example.springboot1.service.ArticleService;
import com.example.springboot1.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        Integer id = (Integer) stringObjectMap.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb=new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        Integer userId = (Integer) stringObjectMap.get("id");
        List<Article> ls=articleMapper.list(userId,categoryId,state);
        Page<Article> p=(Page<Article>) ls;
        pb.setTotal(p.getTotal());
        pb.setItems(ls);
        return pb;
    }

    @Override
    public Article detail(Integer id) {
        Article detail = articleMapper.detail(id);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(detail.getCreateTime());
        System.out.println(LocalDateTime.parse(format));
        detail.setCreateTime(LocalDateTime.parse(format));
        return detail;
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
