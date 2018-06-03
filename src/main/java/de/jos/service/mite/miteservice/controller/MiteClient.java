package de.jos.service.mite.miteservice.controller;

import de.jos.service.mite.miteservice.model.MiteEntry;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Component
public class MiteClient {
    @Value("&{mite.host}")
    private String miteHost;
    private RestTemplate restTemplate;

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String createNewEntry(MiteRequest miteRequest, MiteEntry miteEntry) {
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, miteRequest.getHeaders());
        String url = buildUrl(miteRequest);
        return restTemplate.postForObject(url, entity, String.class);
    }

    private String buildUrl(MiteRequest miteRequest) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setHost(miteHost);
        uriBuilder.setScheme("https");
        uriBuilder.setPath(miteRequest.getPath());
        uriBuilder.setParameter("api_key", miteRequest.getApiKey());
        miteRequest.getSearchParam().ifPresent(searchParam -> uriBuilder.setParameter("name", searchParam));
        try {
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            return "UriSyntaxException";
        }
    }
}
