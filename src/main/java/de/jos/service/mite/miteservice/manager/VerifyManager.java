package de.jos.service.mite.miteservice.manager;

import de.jos.service.mite.miteservice.controller.MiteClient;
import de.jos.service.mite.miteservice.model.MiteRequest;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyManager {
    @Autowired
    private MiteClient miteClient;

    public MiteServiceReply verify(MiteRequest miteRequest) {
        miteClient.callMiteWithRequestAndResponseType(miteRequest, Object.class);
        return new MiteServiceReply(true);
    }
}
