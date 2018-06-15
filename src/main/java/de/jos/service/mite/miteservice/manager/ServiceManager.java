package de.jos.service.mite.miteservice.manager;

import de.jos.service.mite.miteservice.controller.MiteClient;
import de.jos.service.mite.miteservice.model.MiteRequest;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import de.jos.service.mite.miteservice.responseModel.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ServiceManager {
    @Autowired
    private MiteClient miteClient;

    public MiteServiceReply getServices(MiteRequest miteRequest) {
        ServiceResponse[] services = miteClient.callMiteWithRequestAndResponseType(miteRequest, ServiceResponse[].class);

        MiteServiceReply miteServiceReply = new MiteServiceReply(true);
        miteServiceReply.setServices(Arrays.stream(services).map(service -> service.getService()).collect(Collectors.toList()).toArray(new ServiceResponse.Service[services.length]));
        return miteServiceReply;
    }
}
