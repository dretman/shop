package com.shop.retman.web.validator;


import com.shop.retman.domain.Application;
import com.shop.retman.web.service.ApplicationService;
import com.shop.retman.web.utils.CustomFileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ArchiveValidator {

    @Autowired
    private ApplicationService applicationService;
    private String targetFolder;

    public ArchiveValidator(String targetFolder) {
        this.targetFolder = targetFolder;
    }

    public List<String> validate() {
        List<String> validationMessageList = new ArrayList<String>();

        String textFilePresenceValidationMessage = checkTextFilePresence();

        if (textFilePresenceValidationMessage != null) {
            validationMessageList.add(textFilePresenceValidationMessage);
        } else {
            String applicationNamePresenceValidationMessage = validateApplicationNamePresence();
            if (applicationNamePresenceValidationMessage != null) {
                validationMessageList.add(applicationNamePresenceValidationMessage);
            }

            String packageUniquenessValidationMessage = validatePackageUniqueness();
            if (packageUniquenessValidationMessage != null) {
                validationMessageList.add(packageUniquenessValidationMessage);
            }
        }

        return validationMessageList.size() == 0 ? null : validationMessageList;
    }

    private String checkTextFilePresence() {
        String validationMessage = "Txt file is not present in archive";

        String textFileName = CustomFileUtils.retrieveTextFileName(targetFolder);

        if (textFileName != null)
            validationMessage = null;

        return validationMessage;
    }

    private String validateApplicationNamePresence() {
        String validationMessage = "The name of application is not specified in the txt file";

        String txtFile = CustomFileUtils.retrieveTextFileName(targetFolder);

        if (CustomFileUtils.retrieveValue(targetFolder, txtFile, "name") != null) {
            validationMessage = null;
        }

        return validationMessage;
    }

    private String validatePackageUniqueness() {
        String validationMessage = "Package name is not unique";

        String txtFile = CustomFileUtils.retrieveTextFileName(targetFolder);

        String packageName = CustomFileUtils.retrieveValue(targetFolder, txtFile, "package");
        Application application = applicationService.getApplicationByPackageName(packageName);

        if (application == null) {
            validationMessage = null;
        }

        return validationMessage;
    }

}
