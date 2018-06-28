package de.jos.service.mite.miteservice.model;

import de.jos.service.mite.miteservice.responseModel.ProjectResponse;
import de.jos.service.mite.miteservice.responseModel.ServiceResponse;

import java.util.Arrays;

public class MiteServiceReply {
    private boolean success;
    private ServiceResponse.Service[] services;
    private ProjectResponse.Project[] projects;
    private MessageOption[] messageOptions;

    public MiteServiceReply() {
    }

    public MiteServiceReply(boolean success) {
        this.success = success;
    }

    public MiteServiceReply(boolean success, ServiceResponse.Service[] services, ProjectResponse.Project[] projects) {
        this.success = success;
        this.services = services;
        this.projects = projects;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ServiceResponse.Service[] getServices() {
        return services;
    }

    public void setServices(ServiceResponse.Service[] services) {
        this.services = services;
    }

    public ProjectResponse.Project[] getProjects() {
        return projects;
    }

    public void setProjects(ProjectResponse.Project[] projects) {
        this.projects = projects;
    }

    public MessageOption[] getMessageOptions() {
        return messageOptions;
    }

    public void setMessageOptions(MessageOption[] messageOptions) {
        this.messageOptions = messageOptions;
    }

    @Override
    public String toString() {
        return "MiteServiceReply{" +
                "success=" + success +
                ", services=" + Arrays.toString(services) +
                ", projects=" + Arrays.toString(projects) +
                ", messageOptions=" + Arrays.toString(messageOptions) +
                '}';
    }
}
