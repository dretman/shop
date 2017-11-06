package com.shop.retman.web.service;

import com.shop.retman.domain.Application;
import com.shop.retman.domain.Category;
import com.shop.retman.domain.DTO.Downloads;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ApplicationService extends Service {

    public void updateDownloadsQuantity(Long id, Long quantity) {
        Downloads downloads = new Downloads(id, quantity);
        HttpEntity<Object> reqEntity = obtainHttpEntity(downloads);
        template.exchange("http://" + host + ":" + port + applicationName +"/service/app/downloads/quantity", HttpMethod.POST,
                reqEntity, Application.class, downloads);
    }


    public void save(Application application) {
        HttpEntity<Object> reqEntity = obtainHttpEntity(application);
        template.exchange("http://" + host + ":" + port + applicationName +"/service/app", HttpMethod.POST, reqEntity,
                Application.class, application);
    }

    public Application getApplicationById(Long id) {
        HttpEntity<Object> reqEntity = obtainHttpEntity(null);
        ResponseEntity<Application> responseEntity = template.exchange("http://" + host + ":" + port
                        + applicationName + "/service/app/{id}",
                HttpMethod.GET, reqEntity, Application.class, id);

        return responseEntity.getBody();
    }

    public Application getApplicationByPackageName(String packageName) {
        ResponseEntity<Application> responseEntity;

        HttpEntity<Object> reqEntity = obtainHttpEntity(null);
        responseEntity = template.exchange("http://" + host + ":" + port + applicationName
                        + "/service/package?name=" + packageName,
                HttpMethod.GET, reqEntity, Application.class, packageName);

        return responseEntity.getBody();
    }

    public List<Application> getApplicationByCategory(Category category) {
        HttpEntity<Object> reqEntity = obtainHttpEntity(null);
        ParameterizedTypeReference<List<Application>> typeReference = new ParameterizedTypeReference<List<Application>>() {
        };
        ResponseEntity<List<Application>> responseEntity = template.exchange("http://" + host + ":" + port
                + applicationName
                + "/service/application/category/{id}", HttpMethod.GET, reqEntity, typeReference, category.getId());

        return responseEntity.getBody();
    }

    public List<Application> getTopDownloadedApps() {
        HttpEntity<Object> reqEntity = obtainHttpEntity(null);
        ParameterizedTypeReference<List<Application>> typeReference = new ParameterizedTypeReference<List<Application>>() {
        };
        ResponseEntity<List<Application>> responseEntity = template.exchange("http://" + host + ":" + port
                + applicationName
                + "/service/application/top", HttpMethod.GET, reqEntity, typeReference);

        return responseEntity.getBody();
    }


}
