package com.shop.retman.dao.repository;

import com.shop.retman.domain.Application;
import com.shop.retman.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application save(Application application);

    Application findById(Long id);

    void delete(Long id);

    Application getApplicationByPackageName(String packageName);

    List<Application> getApplicationByCategory(Category category);

    List<Application> findTop5ByOrderByDownloadsQuantityDescUploadDateDesc();

    @Modifying
    @Transactional
    @Query("UPDATE APPLICATION APP SET APP.downloadsQuantity = ?2 WHERE ID = ?1")
    void updateDownloadsQuantity(Long id, Long quantity);
}
