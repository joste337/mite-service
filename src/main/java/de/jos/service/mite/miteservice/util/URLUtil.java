package de.jos.service.mite.miteservice.util;

import de.jos.service.mite.miteservice.model.MiteRequest;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;
import java.util.Arrays;

public class URLUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLUtil.class);

    private URLUtil() {
    }

    public static String buildUrlFromMiteRequest(MiteRequest miteRequest, String miteHost, String[]... additionalParams) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setHost(miteHost);
        uriBuilder.setScheme("https");
        uriBuilder.setPath(miteRequest.getPath());
        uriBuilder.setParameter("api_key", miteRequest.getApiKey());
        Arrays.asList(additionalParams).forEach(param -> {
            System.out.println("param: " + param);
            uriBuilder.setParameter(param[0], param[1]);
        });
        miteRequest.getSearchParam().ifPresent(searchParam -> uriBuilder.setParameter("name", searchParam));
        try {
            return uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
            return "";
        }
    }
}
