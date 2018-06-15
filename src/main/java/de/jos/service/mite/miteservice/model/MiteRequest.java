package de.jos.service.mite.miteservice.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Optional;

public class MiteRequest {
    private String path;
    private String apiKey;
    private HttpHeaders headers;
    private Optional<String> searchParam;

    public void buildRequest(String path, MiteRequestAttributes miteRequestAttributes) {
        this.path = path;
        this.apiKey = miteRequestAttributes.getApiKey();
        this.searchParam = Optional.ofNullable(miteRequestAttributes.getSearchParam());
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public Optional<String> getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(Optional<String> searchParam) {
        this.searchParam = searchParam;
    }
}
