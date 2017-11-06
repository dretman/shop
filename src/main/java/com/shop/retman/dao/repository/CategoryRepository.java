package com.shop.retman.dao.repository;

import com.shop.retman.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAll();

    Category findById(long id);
}
