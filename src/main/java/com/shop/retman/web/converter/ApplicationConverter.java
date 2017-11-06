package com.shop.retman.web.converter;

import com.shop.retman.domain.Application;
import com.shop.retman.web.utils.TransformerUtils;
import com.shop.retman.web.utils.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

public class ApplicationConverter implements Converter<Application, File> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConverter.class);
    private String workingDir;

    public ApplicationConverter(String workingDir) {
        this.workingDir = workingDir;
    }

    public File convert(Application application) {
        File archive = null;
        File temporaryWorkingDir = new File(workingDir + new Date().getTime());

        if (temporaryWorkingDir.mkdirs()) {
            File txtFile = createTextFile(temporaryWorkingDir, application);
            File image128 = TransformerUtils.convertByteArrToFile(temporaryWorkingDir, application.getPicture128(),
                    application.getImage128());
            File image512 = TransformerUtils.convertByteArrToFile(temporaryWorkingDir, application.getPicture512(),
                    application.getImage512());

            archive = ZipUtils.zip(temporaryWorkingDir.toString(), temporaryWorkingDir.toString(),
                    application.getApplicationName(), Arrays.asList(txtFile.getName(), image128.getName(), image512.getName()));
        }

        return archive;
    }

    private File createTextFile(File workingFolder, Application application) {
        String fileName = workingFolder + File.separator + application.getApplicationName().concat(".txt");
        File txtFile = new File(fileName);
        String delimiter = "\n";

        try {

            FileUtils.writeStringToFile(txtFile, "name:".concat(application.getApplicationName()).concat(delimiter),
                    Charset.defaultCharset(), true);
            FileUtils.writeStringToFile(txtFile, "package:".concat(application.getPackageName()).concat(delimiter),
                    Charset.defaultCharset(), true);

            if (application.getPicture128() != null) {
                FileUtils.writeStringToFile(txtFile, "picture_128:".concat(application.getPicture128().concat(delimiter)),
                        Charset.defaultCharset(), true);
            }

            if (application.getPicture512() != null) {
                FileUtils.writeStringToFile(txtFile, "picture_512:".concat(application.getPicture512()).concat(delimiter),
                        Charset.defaultCharset(), true);
            }

        } catch (IOException e) {
            LOGGER.error("Error while convert", e);
        }

        return txtFile;
    }

}
