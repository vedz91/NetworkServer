package org.ServerInteractionDemo.api.exception.mapper;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import org.ServerInteractionDemo.api.exception.InvalidOperandException;
import org.ServerInteractionDemo.literal.ExceptionLiterals;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import io.dropwizard.jersey.errors.ErrorMessage;

import static com.codahale.metrics.MetricRegistry.name;

public class InvalidOperandExceptionMapper implements ExceptionMapper<InvalidOperandException> {
    private final Meter exceptions;

    public InvalidOperandExceptionMapper(final MetricRegistry metrics) {
        this.exceptions = metrics.meter(name(this.getClass(), "exceptions"));
    }

    @Override
    public Response toResponse(final InvalidOperandException e) {
        this.exceptions.mark();
        return Response.status(Response.Status.BAD_REQUEST)
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(),
                                                ExceptionLiterals.EM_INVALID_OPERAND_MESSAGE.concat(e.getMessage())))
                       .build();
    }
}
