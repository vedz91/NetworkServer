package org.ServerInteractionDemo.resources;

import org.ServerInteractionDemo.api.OperationResponse;
import org.ServerInteractionDemo.client.DataServiceClient;
import org.ServerInteractionDemo.core.OperationType;
import org.ServerInteractionDemo.literal.SwaggerLiterals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import retrofit2.Call;
import retrofit2.Response;


@Path("/operation")
@Api(SwaggerLiterals.OR_API)
@Builder
public class OperationResource {

    DataServiceClient dataServiceClient;

    @GET
    @ApiOperation(SwaggerLiterals.OR_GET_API)
    @Produces(MediaType.APPLICATION_JSON)
    public OperationResponse compute(
            @QueryParam("type") @ApiParam(defaultValue = "MULTIPLY") final Optional<OperationType> type,
            @QueryParam("opperand1") @ApiParam(required = true) final Double valA,
            @QueryParam("opperand2") @ApiParam(required = true) final Double valB) {
        final Map<String, String> queryparam = new HashMap<String, String>() {{
            put("opperand1", String.valueOf(valA));
            put("opperand2", String.valueOf(valB));
        }};
        if(type.isPresent()){
            queryparam.put("type", type.get().toString());
        }
        Call<OperationResponse> serviceCall = this.dataServiceClient.calculate(queryparam);
        OperationResponse operationResponse = null;
        try {
            Response<OperationResponse> response = serviceCall.execute();
            operationResponse = response.body();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return operationResponse;
    }
}
