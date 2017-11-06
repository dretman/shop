package com.shop.retman.web.utils;

import com.shop.retman.domain.Application;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomFileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFileUtils.class);

    private static void createImage(File imageFolder, String imageName, byte[] bytes) {
        File image = new File(imageFolder + File.separator + imageName);
        try {
            if (image.createNewFile())
                FileUtils.writeByteArrayToFile(image, bytes);
        } catch (IOException e) {
            LOGGER.error("Error while create new image", e);
        }
    }

    public static void retrieveImagesToFolder(String folder, List<Application> applicationList) {
        File imageFolder = new File(folder);
        if (!imageFolder.exists()) {
            imageFolder.mkdir();
        }

        if (imageFolder.exists()) {
            for (Application application : applicationList) {
                createImage(imageFolder, application.getPicture128(), application.getImage128());
                createImage(imageFolder, application.getPicture512(), application.getImage512());
            }
        }
    }

    public static String retrieveTextFileName(String targetFolder) {
        List<String> fileNameList = retrieveFileList(targetFolder);
        String txtFile = null;

        for (String fileName : fileNameList) {
            if (fileName.split("\\.")[1].equals("txt")) {
                txtFile = fileName;
                break;
            }
        }
        return txtFile;
    }

    public static List<String> retrieveFileList(String sourceFolderPath) {
        List<String> result = new ArrayList<String>();
        File sourceFolder = new File(sourceFolderPath);

        if (sourceFolder.isDirectory()) {
            result = Arrays.asList(sourceFolder.list());
        }
        return result;
    }

    public static String retrieveValue(String targetFolder, String txtFile, String key) {
        String value = null;
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(new File(targetFolder.concat(txtFile)));
        } catch (IOException e) {
            LOGGER.error("Error while retrieve value", e);
        }

        for (String line : lines) {
            if (line.split("\\:")[0].equals(key)) {
                value = line.split("\\:")[1];
            }
        }

        if (value != null)
            value = value.trim();

        return value;
    }

}
