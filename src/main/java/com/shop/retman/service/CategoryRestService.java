package com.shop.retman.service;

import com.shop.retman.dao.repository.CategoryRepository;
import com.shop.retman.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class CategoryRestService {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = "application/json")
    public Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }

}
