package org.ServerInteractionDemo.resources;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;


public class StaticResourceTest {

    @Test
    public void testStaticContent() {
        StaticResource staticResource = new StaticResource();

        Response res = staticResource.getData();
        assertEquals("Static Content", res.getEntity());
    }
}
