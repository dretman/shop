package com.shop.retman.web.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class Service {
    String host;
    String port;
    String applicationName;

    @Autowired
    RestTemplate template;

    HttpEntity<Object> obtainHttpEntity(Object body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String plainClientCredentials = userDetails.getUsername() + ":" + userDetails.getPassword();
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

        MultiValueMap<String, String> reqHeaders = new LinkedMultiValueMap<>();
        reqHeaders.add("Accept", "application/json");
        reqHeaders.add("Content-Type", "application/json");
        reqHeaders.add("Authorization", "Basic " + base64ClientCredentials);

        return body == null ? new HttpEntity<>(reqHeaders) : new HttpEntity<>(body, reqHeaders);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
