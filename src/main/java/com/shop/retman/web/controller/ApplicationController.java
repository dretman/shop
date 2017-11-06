package com.shop.retman.web.controller;


import com.shop.retman.domain.Application;
import com.shop.retman.domain.Category;
import com.shop.retman.web.ApplicationHandler;
import com.shop.retman.web.ApplicationProperties;
import com.shop.retman.web.service.CategoryService;
import com.shop.retman.web.utils.CustomFileUtils;
import com.shop.retman.web.utils.ZipUtils;
import com.shop.retman.web.validator.ArchiveValidator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Scope("session")
public class ApplicationController extends com.shop.retman.web.controller.Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ArchiveValidator archiveValidator;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/app/{id}", method = RequestMethod.GET)
    public String processAppRetrieve(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Application application = applicationService.getApplicationById(id);
        request.getSession().setAttribute("app", application);

        CustomFileUtils.retrieveImagesToFolder(dynamicApplicationProperties.getUploadedImagesFolder(), Arrays.asList(application));

        model.addAttribute("app", application);
        provideModelWithTopDownloadedApps(model);
        addBasicConfigurationToModel(model);

        return "details";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String processCategory(@PathVariable("id") Long id, Model model) {
        List<Category> categoryList = categoryService.findAll();

        Category category = new Category();
        category.setId(id);

        List<Application> applicationList = applicationService.getApplicationByCategory(category);

        CustomFileUtils.retrieveImagesToFolder(dynamicApplicationProperties.getUploadedImagesFolder(), applicationList);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("applicationList", applicationList);
        provideModelWithTopDownloadedApps(model);

        return "home";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String processUploadPage(Model model) {
        List<Category> categoryList = categoryService.findAll();

        Application application = new Application();
        Category category = new Category();
        application.setCategory(category);

        addBasicConfigurationToModel(model);
        model.addAttribute("application", application);
        model.addAttribute("categoryList", categoryList);

        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String processSaveApplication(@RequestPart("archive") MultipartFile archive,
                                         @Valid Application application,
                                         Errors errors,
                                         Model model) {
        addBasicConfigurationToModel(model);

        File uploadsFolder = new File(ApplicationProperties.UPLOAD_FOLDER);
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdir();
        }

        File file = new File(ApplicationProperties.UPLOAD_FOLDER.concat(archive.getOriginalFilename()));
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("File creation failed", e);
        }

        List<String> validationResult = new ArrayList<String>();
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        provideModelWithTopDownloadedApps(model);

        try {
            archive.transferTo(file);

            ZipUtils.unZip(ApplicationProperties.UPLOAD_FOLDER.concat(archive.getOriginalFilename()),
                    ApplicationProperties.TEMPORARY_FOLDER);

            /* ------------ Validation ------------*/

            validationResult = archiveValidator.validate();

            if (validationResult != null) {
                model.addAttribute("archiveValidationErrorMessage", validationResult);
                return "upload";
            } else {
                ApplicationHandler applicationHandler = new ApplicationHandler(ApplicationProperties.TEMPORARY_FOLDER);
                Application resultApplication = applicationHandler.handle(application);
                applicationService.save(resultApplication);
            }

            FileUtils.deleteDirectory(new File(ApplicationProperties.TEMPORARY_FOLDER));
            FileUtils.deleteDirectory(new File(ApplicationProperties.UPLOAD_FOLDER));

        } catch (FileNotFoundException e) {
            LOGGER.error("Error while file upload [could not find the file]", e);
            if (validationResult != null)
                validationResult.add("Error while file upload");

            model.addAttribute("archiveValidationErrorMessage", validationResult);
            return "upload";
        } catch (IOException e) {
            LOGGER.error("Error while file upload [could not find the file]", e);
            if (validationResult != null)
                validationResult.add("Choose file to upload");
            model.addAttribute("archiveValidationErrorMessage", validationResult);
            return "upload";
        }


        if (errors.hasErrors()) {
            return "upload";
        }

        return "redirect:/";
    }

}
