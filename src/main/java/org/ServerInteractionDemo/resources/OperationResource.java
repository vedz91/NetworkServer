package org.ServerInteractionDemo.resources;

import org.ServerInteractionDemo.api.OperationRequest;
import org.ServerInteractionDemo.api.OperationResponse;
import org.ServerInteractionDemo.api.exception.DataServiceException;
import org.ServerInteractionDemo.client.DataServiceClient;
import org.ServerInteractionDemo.core.OperationType;
import org.ServerInteractionDemo.literal.ExceptionLiterals;
import org.ServerInteractionDemo.literal.SwaggerLiterals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import retrofit2.Call;


@Path("/operation")
@Api(SwaggerLiterals.OR_API)
@Builder
public class OperationResource {

    final static Logger LOGGER = LoggerFactory.getLogger(OperationResource.class);

    DataServiceClient dataServiceClient;

    @GET
    @ApiOperation(SwaggerLiterals.OR_GET_API)
    @Produces(MediaType.APPLICATION_JSON)
    public OperationResponse compute(
            @QueryParam("type") @ApiParam(defaultValue = "MULTIPLY") final Optional<OperationType> type,
            @QueryParam("operand1") @ApiParam(required = true) final Double valA,
            @QueryParam("operand2") @ApiParam(required = true) final Double valB) {

        OperationRequest opRequest = OperationRequest.builder()
                                                     .type(type)
                                                     .valA(valA)
                                                     .valB(valB)
                                                     .build();
        opRequest.validate();

        try {
            Call<OperationResponse> serviceCall = this.dataServiceClient.calculate(opRequest.toQueryMap());
            LOGGER.info("[Request] Calling: " + serviceCall.request().url().toString());
            return serviceCall.execute().body();
        }
        catch (IOException e) {
            LOGGER.error(ExceptionLiterals.E_DS_IO_ERROR.concat(e.getMessage()));
            if(e.getMessage().toLowerCase().contains("failed to connect")) {
                throw new DataServiceException(ExceptionLiterals.E_DS_SERVICE_UNAVAILABLE,
                                               Response.Status.SERVICE_UNAVAILABLE);
            }
            //Unknown Exception -> JAXRS dpes not have any supported type for unknown Exception hence INTERNAL_SERVER_ERROR
            throw new DataServiceException(ExceptionLiterals.E_DS_SERVICE_UNKNOWN,
                                           Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
