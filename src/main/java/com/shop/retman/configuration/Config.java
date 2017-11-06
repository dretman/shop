package com.shop.retman.configuration;

import com.shop.retman.web.DynamicApplicationProperties;
import java.io.File;

public abstract class Config {

    public DynamicApplicationProperties generateDynamicApplicationProperties(String uploadedImagesDir, String applicationName, String userDir) {
        DynamicApplicationProperties dynamicApplicationProperties = new DynamicApplicationProperties();
        String resource = File.separator + "resources" + File.separator + uploadedImagesDir;
        String uploadedImagesFolderRelativePath = applicationName + resource;

        dynamicApplicationProperties.setUploadedImagesFolderRelativePath(uploadedImagesFolderRelativePath);
        dynamicApplicationProperties.setUploadedImagesFolder(userDir.concat(resource));

        return dynamicApplicationProperties;
    }
}
