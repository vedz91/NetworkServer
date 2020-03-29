package org.ServerInteractionDemo.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class DataServiceException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceException.class);
    private static final long serialVersionUID = -6810296759976206442L;
    private Response.Status statusCode;


    public DataServiceException(final String message) {
        super(message);
        this.statusCode = Response.Status.INTERNAL_SERVER_ERROR;
        LOGGER.error("DataServiceException: " + message);
    }

    public DataServiceException(final String message, final Response.Status code) {
        super(message);
        this.statusCode = code;
        LOGGER.error("DataServiceException: " + message);
    }

    public Response.Status getStatusCode() {
        return statusCode;
    }
}