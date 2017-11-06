package com.shop.retman.web.controller;

import com.shop.retman.domain.Application;
import com.shop.retman.web.DynamicApplicationProperties;
import com.shop.retman.web.service.ApplicationService;
import com.shop.retman.web.utils.CustomFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.List;

public class Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Value("${app.host}")
    private String host;

    @Value("${server.context-path}")
    protected String applicationName;

    @Autowired
    protected ApplicationService applicationService;

    @Autowired
    DynamicApplicationProperties dynamicApplicationProperties;

    void provideModelWithTopDownloadedApps(Model model) {
        List<Application> applicationTopList = applicationService.getTopDownloadedApps();
        LOGGER.info("UPLOADED IMGES FOLDER : ".concat(dynamicApplicationProperties.getUploadedImagesFolder()));
        CustomFileUtils.retrieveImagesToFolder(dynamicApplicationProperties.getUploadedImagesFolder(), applicationTopList);
        model.addAttribute("applicationTopList", applicationTopList);
    }

    void addBasicConfigurationToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        model.addAttribute("userName", userDetails.getUsername());
        model.addAttribute("host", host);
        model.addAttribute("applicationName", applicationName);
        model.addAttribute("uploadedImagesFolder", dynamicApplicationProperties.getUploadedImagesFolderRelativePath());
    }
}
