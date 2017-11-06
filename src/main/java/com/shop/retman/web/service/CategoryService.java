package com.shop.retman.web.service;


import com.shop.retman.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CategoryService extends Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);


    public List<Category> findAll() {
        HttpEntity<Object> reqEntity = obtainHttpEntity(null);
        ParameterizedTypeReference<List<Category>> typeReference = new ParameterizedTypeReference<List<Category>>() {
        };

        String url = "http://" + host + ":" + port + applicationName + "/service/category";
        LOGGER.debug("Find all categories URL [ " + url + " ]");

        ResponseEntity<List<Category>> responseEntity = template.exchange(url, HttpMethod.GET, reqEntity, typeReference);

        return responseEntity.getBody();
    }

}
