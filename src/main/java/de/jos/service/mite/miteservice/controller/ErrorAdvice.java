package de.jos.service.mite.miteservice.controller;

import de.jos.service.mite.miteservice.model.MiteServiceReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;


@ControllerAdvice
public class ErrorAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler({RestClientException.class})
    @ResponseBody
    public MiteServiceReply handleRestClientException(RestClientException exception) {
        LOGGER.error("RestClientException: {}", exception.getMessage());
        return new MiteServiceReply(false);
    }
}
