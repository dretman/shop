package com.shop.retman.web;

import com.shop.retman.domain.Application;
import com.shop.retman.web.utils.TransformerUtils;
import com.shop.retman.web.utils.CustomFileUtils;

import java.io.File;
import java.util.Date;

public class ApplicationHandler {
    private String targetFolder;

    public ApplicationHandler(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public Application handle(Application application) {

        String textFileName = CustomFileUtils.retrieveTextFileName(targetFolder);

        String applicationName = CustomFileUtils.retrieveValue(targetFolder, textFileName, ApplicationProperties.APPLICATION_NAME);
        String applicationPackage = CustomFileUtils.retrieveValue(targetFolder, textFileName, ApplicationProperties.APPLICATION_PACKAGE);
        String smallPicture = CustomFileUtils.retrieveValue(targetFolder, textFileName, ApplicationProperties.SMALL_PICTURE);
        String bigPicture = CustomFileUtils.retrieveValue(targetFolder, textFileName, ApplicationProperties.BIG_PICTURE);

        application.setApplicationName(applicationName);
        application.setPackageName(applicationPackage);
        application.setUploadDate(new Date());

        String smallPictureFullPath;
        String bigPictureFullPath;

        if (smallPicture == null) {
            smallPicture = "smile_128.jpg";
            smallPictureFullPath = ApplicationProperties.DEFAULT_FOLDER.concat(smallPicture);
        } else {
            smallPictureFullPath = ApplicationProperties.TEMPORARY_FOLDER.concat(smallPicture);
        }

        if (bigPicture == null) {
            bigPicture = "smile_512.jpg";
            bigPictureFullPath = ApplicationProperties.DEFAULT_FOLDER.concat(bigPicture);
        } else {
            bigPictureFullPath = ApplicationProperties.TEMPORARY_FOLDER.concat(bigPicture);
        }

        application.setPicture128(smallPicture);
        application.setPicture512(bigPicture);

        File image128 = new File(smallPictureFullPath);
        application.setImage128(TransformerUtils.convertFileToByteArr(image128));

        File image512 = new File(bigPictureFullPath);
        application.setImage512(TransformerUtils.convertFileToByteArr(image512));

        application.setDownloadsQuantity(0L);

        return application;
    }
}
