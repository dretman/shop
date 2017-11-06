package com.shop.retman.web;

import java.io.File;

public class ApplicationProperties {
    public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
    public static final String TEMPORARY_FOLDER = System.getProperty("user.dir") + File.separator + "temporary" + File.separator;
    public static final String DEFAULT_FOLDER = System.getProperty("user.dir") + File.separator + "default" + File.separator;
    public static final String APPLICATION_NAME = "name";
    public static final String APPLICATION_PACKAGE = "package";
    public static final String SMALL_PICTURE = "picture_128";
    public static final String BIG_PICTURE = "picture_512";
}
