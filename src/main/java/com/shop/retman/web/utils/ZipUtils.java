package com.shop.retman.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformerUtils.class);

    public static File zip(String sourceFolder, String outputFolder, String zipArchiveName, List<String> fileNames) {
        File archiveFile = null;
        byte[] buffer = new byte[1024];

        try {
            archiveFile = new File(outputFolder + File.separator + zipArchiveName + ".zip");
            new File(archiveFile.getParent()).mkdirs();
            archiveFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(archiveFile);
            try (ZipOutputStream zos = new ZipOutputStream(outputStream)) {

                for (String fileName : fileNames) {
                    ZipEntry ze = new ZipEntry(fileName);
                    zos.putNextEntry(ze);

                    try (FileInputStream inputStream = new FileInputStream(sourceFolder + File.separator + fileName)) {

                        int len;
                        while ((len = inputStream.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }

                    }
                }

                zos.closeEntry();
            }

        } catch (IOException e) {
            LOGGER.error("Error while unzip", e);
        }

        return archiveFile;
    }

    public static void unZip(String inputZipFile, String outputFolder) throws IOException {
        byte[] buffer = new byte[1024];

            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(inputZipFile))) {
                ZipEntry ze = zis.getNextEntry();

                while (ze != null) {
                    String fileName = ze.getName();
                    File newFile = new File(outputFolder + File.separator + fileName);

                    new File(newFile.getParent()).mkdirs();

                    try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, len);
                        }
                    }

                    ze = zis.getNextEntry();
                }

                zis.closeEntry();
            }

    }
}
