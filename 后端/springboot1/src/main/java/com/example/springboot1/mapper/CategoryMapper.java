package com.example.springboot1.mapper;

import com.example.springboot1.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category (category_name,category_alias,create_user,create_time,update_time) values (#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);
    @Select("SELECT 1 FROM category WHERE category_name = #{categoryName} OR category_alias = #{categoryAlias} LIMIT 1 ")
    Integer checkCategoryName(String categoryName, String categoryAlias);

    @Select("select * from category")
    List<Category> list();

    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id =#{id}")
    void update(Category category);

    @Delete("delete from category where id=#{id}")
    void delete(Integer id);
}
