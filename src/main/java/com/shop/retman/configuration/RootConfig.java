package com.shop.retman.configuration;

import com.shop.retman.web.ApplicationProperties;
import com.shop.retman.web.converter.CategoryConverter;
import com.shop.retman.web.service.ApplicationService;
import com.shop.retman.web.service.CategoryService;
import com.shop.retman.web.validator.ArchiveValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RootConfig {

    @Value("${app.host}")
    private String host;

    @Value("${server.port}")
    private String port;

    @Value("${server.context-path}")
    private String applicationName;


    @Bean
    CategoryConverter categoryConverter() {
        return new CategoryConverter();
    }

    @Bean
    ApplicationService applicationService() {
        ApplicationService applicationService = new ApplicationService();
        applicationService.setHost(host);
        applicationService.setPort(port);
        applicationService.setApplicationName(applicationName);
        return applicationService;
    }

    @Bean
    CategoryService categoryService() {
        CategoryService categoryService = new CategoryService();
        categoryService.setHost(host);
        categoryService.setPort(port);
        categoryService.setApplicationName(applicationName);
        return categoryService;
    }

    @Bean
    ArchiveValidator archiveValidator() {
        return new ArchiveValidator(ApplicationProperties.TEMPORARY_FOLDER);
    }

    @Bean
    RestTemplate template() {
        return new RestTemplate();
    }
}