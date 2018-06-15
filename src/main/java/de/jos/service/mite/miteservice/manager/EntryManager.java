package de.jos.service.mite.miteservice.manager;

import de.jos.service.mite.miteservice.controller.MiteClient;
import de.jos.service.mite.miteservice.model.*;
import de.jos.service.mite.miteservice.responseModel.TimeEntriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EntryManager {
    @Autowired
    private MiteClient miteClient;

    public MiteServiceReply createNewEntry(MiteRequest miteRequest, MiteEntry miteEntry) {
        HttpEntity<MiteEntry> entity = new HttpEntity<>(miteEntry, miteRequest.getHeaders());
        miteClient.mitePostRequestWithEntity(miteRequest, entity, Object.class);
        return new MiteServiceReply(true);
    }

    public MiteServiceReply getPastEntries(MiteRequest miteRequest) {
        TimeEntriesResponse[] entries = miteClient.callMiteWithRequestAndResponseType(miteRequest, TimeEntriesResponse[].class, new String[] {"limit", "9"});
        Object entriesObject = miteClient.callMiteWithRequestAndResponseType(miteRequest, Object.class, new String[] {"limit", "9"});

        Arrays.asList(entries).forEach(entry -> System.out.println("mite reply: " + entry.getTime_entry().toReplyString()));
        System.out.println("entries object: " + entriesObject);

        MiteServiceReply miteServiceReply = new MiteServiceReply(true);
        List<MessageOption> messageOptions = new ArrayList<>();
        Arrays.asList(entries).forEach(timeEntry -> messageOptions.add(new MessageOption(timeEntry.getTime_entry().toReplyString(), "url with timeEntryId: " + timeEntry.getTime_entry().getId())));
        miteServiceReply.setMessageOption(messageOptions.toArray(new MessageOption[messageOptions.size()]));


        return miteServiceReply;
    }

    public MiteEntry parseMiteRequestAttributesToMiteEntry(MiteRequestAttributes miteRequestAttributes) {
        return new MiteEntry(miteRequestAttributes.getDuration(),
                miteRequestAttributes.getComment(),
                miteRequestAttributes.getProjectId(),
                miteRequestAttributes.getServiceId());
    }
}
