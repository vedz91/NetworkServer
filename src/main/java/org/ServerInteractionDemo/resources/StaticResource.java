package org.ServerInteractionDemo.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Static Endpoint")
@Path("/static")
public class StaticResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(StaticResource.class);

    @GET
    @ApiOperation("Get static Content")
    @Produces(MediaType.TEXT_HTML)
    public Response getData() {
        LOGGER.info("Received Request @ " + LocalDateTime.now());
        return Response.ok("Static Content").build();
    }


}
