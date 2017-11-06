package com.shop.retman.web.controller;

import com.shop.retman.domain.Application;
import com.shop.retman.web.ApplicationProperties;
import com.shop.retman.web.converter.ApplicationConverter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/download")
public class FileDownloadController extends com.shop.retman.web.controller.Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @Transactional("transactionManager")
    public void downloadResource(HttpServletRequest request, HttpServletResponse response) {
        Application application = (Application) request.getSession().getAttribute("app");

        ApplicationConverter applicationConverter = new ApplicationConverter(ApplicationProperties.TEMPORARY_FOLDER);
        File archive = applicationConverter.convert(application);

        response.setContentType("application/octet-stream");

        response.addHeader("Content-Description", "File Transfer");
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Content-Disposition", String.format("attachment;filename=%s", application.getPackageName()));
        response.addHeader("Content-Transfer-Encoding", "binary");
        response.addHeader("Expires", "0");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Pragma", "public");
        response.addHeader("Content-Length", String.valueOf(archive.length()));

        try {
            Files.copy(archive.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            FileUtils.deleteDirectory(new File(ApplicationProperties.TEMPORARY_FOLDER));

            Long downloadsQuantity = application.getDownloadsQuantity() == null ? 1 : (application.getDownloadsQuantity() + 1L);
            applicationService.updateDownloadsQuantity(application.getId(), downloadsQuantity);
        } catch (IOException e) {
            LOGGER.error("Error while downloading resource", e);
        }
    }
}
