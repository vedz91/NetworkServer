package org.ServerInteractionDemo.client;

import org.ServerInteractionDemo.api.OperationResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DataServiceClient {

    @GET("/operation")
    public Call<OperationResponse> calculate(@QueryMap Map<String, String> params);

}
