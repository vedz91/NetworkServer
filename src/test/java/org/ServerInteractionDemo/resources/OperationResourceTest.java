package org.ServerInteractionDemo.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ServerInteractionDemo.api.OperationResponse;
import org.ServerInteractionDemo.api.exception.DataServiceException;
import org.ServerInteractionDemo.api.exception.InvalidOperandException;
import org.ServerInteractionDemo.client.DataServiceClient;
import org.ServerInteractionDemo.core.OperationType;
import org.ServerInteractionDemo.core.retrofit.RetrofitClient;
import org.ServerInteractionDemo.core.retrofit.RetrofitConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.ServerInteractionDemo.core.retrofit.RetrofitClient.retrofitClient;
import static org.junit.Assert.assertEquals;


public class OperationResourceTest {

    private final MockWebServer server = new MockWebServer();

    private final ObjectMapper mapper = new ObjectMapper();

    private final RetrofitClient<DataServiceClient> dataServiceClient = retrofitClient(DataServiceClient.class);

    private DataServiceClient client;

    private OperationResource resource;

    @Before
    public void setup() throws IOException {
        this.server.start();
        this.client = this.dataServiceClient.get(RetrofitConfiguration.builder()
                                                                      .url(this.server.url("/").toString())
                                                                      .build());
        this.resource = OperationResource.builder().dataServiceClient(this.client).build();
    }

    @After
    public void teardown() throws IOException {
        this.server.shutdown();
    }

    @Test
    public void computeMock() throws JsonProcessingException {
        OperationResponse mockResp = OperationResponse.builder().type(OperationType.MULTIPLY).result(6.0).build();
        this.server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                                              .setBody(this.mapper.writeValueAsString(mockResp)));

        OperationResponse resp = this.resource.compute(Optional.empty(), 2.0, 3.0);

        assertEquals(mockResp.getType(), resp.getType());
        assertEquals(0, mockResp.getResult().compareTo(resp.getResult()));
    }

    @Test
    public void computeAddMock() throws JsonProcessingException {
        OperationResponse mockResp = OperationResponse.builder().type(OperationType.ADD).result(5.0).build();
        this.server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
                                              .setBody(this.mapper.writeValueAsString(mockResp)));

        OperationResponse resp = this.resource.compute(Optional.empty(), 2.0, 3.0);

        assertEquals(mockResp.getType(), resp.getType());
        assertEquals(0, mockResp.getResult().compareTo(resp.getResult()));
    }

    @Test(expected = InvalidOperandException.class)
    public void computeThrowsInvalidOperand() {
        this.resource.compute(Optional.empty(), 2.0, null);
    }

    @Test(expected = DataServiceException.class)
    public void computeThrowsDataServerException() {
        this.server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
                                              .setBody("Exception in Server"));
        this.resource.compute(Optional.empty(), 2.0, 3.0);

    }
}
