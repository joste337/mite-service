package de.jos.service.mite.miteservice.controller;

import de.jos.service.mite.miteservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static de.jos.service.mite.miteservice.util.URLUtil.buildUrlFromMiteRequest;

@Component
public class MiteClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteClient.class);

    @Value("${mite.host}")
    private String miteHost;
    private RestTemplate restTemplate;

    // private String mtr3ID = "2351287";
    // private String developmentID = "253445";

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public <T> T callMiteWithRequestAndResponseType(MiteRequest miteRequest, Class<T> responseType, String... parameters) {
        String url = buildUrlFromMiteRequest(miteRequest, miteHost, parameters);
        return restTemplate.getForObject(url, responseType);
    }

    public <T,P> P mitePostRequestWithEntity(MiteRequest miteRequest, HttpEntity<T> entity, Class<P> responseType) {
        String url = buildUrlFromMiteRequest(miteRequest, miteHost);
        return restTemplate.postForObject(url, entity, responseType);
    }
}
