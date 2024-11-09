package com.example.springboot1.controller;

import com.example.springboot1.pojo.Category;
import com.example.springboot1.pojo.Result;
import com.example.springboot1.service.CategoryService;
import com.example.springboot1.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        boolean flag = categoryService.add(category);
        if (flag){
            return  Result.success();
        }
        return Result.error("已存在该目录");
    }

    @GetMapping
    public Result<List<Category>> list(){
        return Result.success(categoryService.list());
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id){
        if (id==null){
            return Result.error("参数错误");
        }
        return Result.success(categoryService.detail(id));
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.update.class) Category category){
        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        Integer userId = (Integer) stringObjectMap.get("id");
        Integer detailId = categoryService.detail(category.getId()).getCreateUser();
        if (!userId.equals(detailId)){
            return Result.error("没有权限");
        }
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        System.out.println(id);
        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        Integer userId = (Integer) stringObjectMap.get("id");
        Integer detailId = categoryService.detail(id).getCreateUser();
        if (!userId.equals(detailId)){
            return Result.error("没有权限");
        }
        categoryService.delete(id);
        return Result.success();
    }
}
