package com.shop.retman.web;

public class DynamicApplicationProperties {
    private String uploadedImagesFolder = "[UPLOADED IMAGES FOLDER]";
    private String uploadedImagesFolderRelativePath = "[UPLOADED IMAGES FOLDER RELATIVE PATH]";

    public String getUploadedImagesFolder() {
        return uploadedImagesFolder;
    }

    public void setUploadedImagesFolder(String uploadedImagesFolder) {
        this.uploadedImagesFolder = uploadedImagesFolder;
    }

    public String getUploadedImagesFolderRelativePath() {
        return uploadedImagesFolderRelativePath;
    }

    public void setUploadedImagesFolderRelativePath(String uploadedImagesFolderRelativePath) {
        this.uploadedImagesFolderRelativePath = uploadedImagesFolderRelativePath;
    }

    @Override
    public String toString() {
        return "DynamicApplicationProperties{" +
                "uploadedImagesFolder='" + uploadedImagesFolder + '\'' +
                ", uploadedImagesFolderRelativePath='" + uploadedImagesFolderRelativePath + '\'' +
                '}';
    }
}