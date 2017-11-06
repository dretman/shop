package com.shop.retman.service;

import com.shop.retman.dao.repository.ApplicationRepository;
import com.shop.retman.domain.Application;
import com.shop.retman.domain.Category;
import com.shop.retman.domain.DTO.Downloads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class ApplicationRestService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @RequestMapping(value = "/app/downloads/quantity", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Application> updateDownloadsQuantity(@RequestBody Downloads downloads) {
        applicationRepository.updateDownloadsQuantity(downloads.getApplicationId(), downloads.getDownloadsQuantity());

        Application applicationResult = new Application();
        applicationResult.setId(downloads.getApplicationId());
        applicationResult.setDownloadsQuantity(downloads.getDownloadsQuantity());

        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Application>(applicationResult, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Application> save(@RequestBody Application application) {

        Application applicationResult = applicationRepository.save(application);
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Application>(applicationResult, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/package", method = RequestMethod.GET, produces = "application/json")
    public Application getApplicationByPackageName(@RequestParam("name") String packageName) {
        return applicationRepository.getApplicationByPackageName(packageName);
    }

    @RequestMapping(value = "/app/{id}", method = RequestMethod.GET, produces = "application/json")
    public Application getApplicationById(@PathVariable("id") Long id) {
        return applicationRepository.findById(id);
    }


    @RequestMapping(value = "/application/category/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Application> getApplicationByCategory(@PathVariable("id") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return applicationRepository.getApplicationByCategory(category);
    }

    @RequestMapping(value = "/application/top", method = RequestMethod.GET, produces = "application/json")
    public List<Application> getTopDownloadedApps() {
        return applicationRepository.findTop5ByOrderByDownloadsQuantityDescUploadDateDesc();
    }

}
