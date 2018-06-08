package de.jos.service.mite.miteservice.manager;

import de.jos.service.mite.miteservice.controller.MiteClient;
import de.jos.service.mite.miteservice.model.MiteEntry;
import de.jos.service.mite.miteservice.model.MiteRequest;
import de.jos.service.mite.miteservice.model.MiteRequestAttributes;
import de.jos.service.mite.miteservice.model.MiteServiceReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class EntryManager {
    @Autowired
    private MiteClient miteClient;

    public MiteServiceReply createNewEntry(MiteRequest miteRequest, MiteEntry miteEntry) {
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, miteRequest.getHeaders());
        miteClient.mitePostRequestWithEntity(miteRequest, entity, Object.class);
        return new MiteServiceReply(true);
    }

    public MiteEntry parseMiteRequestAttributesToMiteEntry(MiteRequestAttributes miteRequestAttributes) {
        return new MiteEntry(miteRequestAttributes.getDuration(),
                miteRequestAttributes.getComment(),
                miteRequestAttributes.getProjectId(),
                miteRequestAttributes.getServiceId());
    }
}
