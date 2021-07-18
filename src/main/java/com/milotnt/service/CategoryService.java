package com.milotnt.service;

import com.milotnt.pojo.Category;
import com.milotnt.util.Page;

import java.util.List;

public interface CategoryService {

    //List<Category> list(Page page);

    //int total();

    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);

}
