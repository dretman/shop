package com.shop.retman.web.converter;


import com.shop.retman.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryConverter implements Converter<String, Category> {

    public Category convert(String categoryIdStr) {
        Category result = new Category();
        Long id = Long.valueOf(categoryIdStr);
        result.setId(id);
        return result;
    }
}
