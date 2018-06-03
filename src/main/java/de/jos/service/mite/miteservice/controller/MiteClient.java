package de.jos.service.mite.miteservice.controller;

import de.jos.service.mite.miteservice.model.MiteEntry;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import de.jos.service.mite.miteservice.model.ProjectResponse;
import de.jos.service.mite.miteservice.model.ServiceResponse;
import org.apache.http.client.utils.URIBuilder;
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
    @Value("&{mite.host}")
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
        try {
            restTemplate.postForObject(url, entity, String.class);
            return new MiteServiceReply(true);
        } catch (Exception e) {
            return new MiteServiceReply(false);
        }
    }

    public MiteServiceReply getProjects(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);
        ProjectResponse[] projects;

        try {
            projects = restTemplate.getForObject(url, ProjectResponse[].class);
            MiteServiceReply miteServiceReply = new MiteServiceReply(true);
            List<ProjectResponse.Project> projectList = new ArrayList<>();
            Arrays.asList(projects).forEach(project -> {
                projectList.add(project.getProject());
            });
            miteServiceReply.setProjects(projectList.toArray(new ProjectResponse.Project[projectList.size()]));
            return miteServiceReply;
        } catch (Exception e) {
            return new MiteServiceReply(false);
        }
    }

    public MiteServiceReply getServices(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);
        ServiceResponse[] services;

        try {
            services = restTemplate.getForObject(url, ServiceResponse[].class);
            MiteServiceReply miteServiceReply = new MiteServiceReply(true);
            List<ServiceResponse.Service> serviceList = new ArrayList<>();
            Arrays.asList(services).forEach(service -> {
                serviceList.add(service.getService());
            });
            miteServiceReply.setServices(serviceList.toArray(new ServiceResponse.Service[serviceList.size()]));
            return miteServiceReply;
        } catch (Exception e) {
            return new MiteServiceReply(false);
        }
    }

    public MiteServiceReply verify(MiteRequest miteRequest) {
        String url = buildUrl(miteRequest);

        try {
            restTemplate.getForObject(url, Object.class);
            return new MiteServiceReply(true);
        } catch (Exception e) {
            return new MiteServiceReply(false);
        }
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
