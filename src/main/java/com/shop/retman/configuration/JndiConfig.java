package com.shop.retman.configuration;

import com.shop.retman.web.DynamicApplicationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Configuration
@Profile("jndi")
public class JndiConfig extends Config {

    @Value("${uploaded.images.folder}")
    private String uploadedImagesDir;
    @Value("${server.context-path}")
    private String applicationName;

    @Bean
    DynamicApplicationProperties dynamicApplicationProperties() {
        String userDir = System.getProperty("user.dir").replace("bin", "") + File.separator
                + "webapps" + applicationName;

        return generateDynamicApplicationProperties(uploadedImagesDir, applicationName, userDir);
    }
}
