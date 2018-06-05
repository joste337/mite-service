package de.jos.service.mite.miteservice.controller;

import de.jos.service.mite.miteservice.model.MiteEntry;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import de.jos.service.mite.miteservice.model.ProjectResponse;
import de.jos.service.mite.miteservice.model.ServiceResponse;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MiteClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiteService.class);

    @Value("${mite.host}")
    private String miteHost;
    private RestTemplate restTemplate;

    // private String mtr3ID = "2351287";
    // private String developmentID = "253445";

    public MiteClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public MiteServiceReply createNewEntry(MiteRequest miteRequest, MiteEntry miteEntry) {
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, miteRequest.getHeaders());
        String url = buildUrl(miteRequest);

        restTemplate.postForObject(url, entity, String.class);
        return new MiteServiceReply(true);
    }

    public MiteServiceReply getProjects(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);
        ProjectResponse[] projects;

        projects = restTemplate.getForObject(url, ProjectResponse[].class);
        MiteServiceReply miteServiceReply = new MiteServiceReply(true);
        List<ProjectResponse.Project> projectList = new ArrayList<>();
        Arrays.asList(projects).forEach(project -> {
            projectList.add(project.getProject());
        });
        miteServiceReply.setProjects(projectList.toArray(new ProjectResponse.Project[projectList.size()]));
        LOGGER.info("Returning miteServiceReply: {}", miteServiceReply.toString());
        return miteServiceReply;
    }

    public MiteServiceReply getServices(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);
        ServiceResponse[] services;

        services = restTemplate.getForObject(url, ServiceResponse[].class);
        MiteServiceReply miteServiceReply = new MiteServiceReply(true);
        List<ServiceResponse.Service> serviceList = new ArrayList<>();
        Arrays.asList(services).forEach(service -> {
            serviceList.add(service.getService());
        });
        miteServiceReply.setServices(serviceList.toArray(new ServiceResponse.Service[serviceList.size()]));
        LOGGER.info("Returning miteServiceReply: {}", miteServiceReply.toString());
        return miteServiceReply;
    }

    public MiteServiceReply verify(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);

        restTemplate.getForObject(url, Object.class);
        return new MiteServiceReply(true);
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
            LOGGER.error(e.getMessage());
            return "";
        }
    }
}
