package org.ServerInteractionDemo.api.exception.mapper;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import org.ServerInteractionDemo.api.exception.DataServiceException;
import org.ServerInteractionDemo.literal.ExceptionLiterals;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import io.dropwizard.jersey.errors.ErrorMessage;

import static com.codahale.metrics.MetricRegistry.name;

public class DataServiceExceptionMapper implements ExceptionMapper<DataServiceException> {

    private final Meter exceptions;

    public DataServiceExceptionMapper(final MetricRegistry metrics) {
        this.exceptions = metrics.meter(name(this.getClass(), "exceptions"));
    }


    @Override
    public Response toResponse(DataServiceException e) {
        this.exceptions.mark();
        return Response.status(e.getStatusCode())
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .entity(new ErrorMessage(e.getStatusCode().getStatusCode(),
                                                ExceptionLiterals.EM_DATA_SERVICE_MESSAGE.concat(e.getMessage())))
                       .build();
    }
}
