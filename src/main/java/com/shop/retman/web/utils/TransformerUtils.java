package com.shop.retman.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TransformerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformerUtils.class);

    public static byte[] convertFileToByteArr(File file) {

        byte[] byteArr = new byte[(int) file.length()];

        try {

            try (FileInputStream inputStream = new FileInputStream(file)) {
                inputStream.read(byteArr);
            }

        } catch (Exception e) {
            LOGGER.error("Error while convert file to byte array", e);
        }

        return byteArr;
    }

    public static File convertByteArrToFile(File fileLocation, String fileName, byte[] byteArr) {
        File file = new File(fileLocation.toString().concat(File.separator).concat(fileName));

        try (FileOutputStream outputStream = new FileOutputStream(file)){
            outputStream.write(byteArr);
        } catch (Exception e) {
            LOGGER.error("Error while convert byte array to file", e);
        }

        return file;
    }
}
