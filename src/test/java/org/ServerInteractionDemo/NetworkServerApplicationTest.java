package org.ServerInteractionDemo;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

import org.ServerInteractionDemo.core.retrofit.RetrofitConfiguration;
import org.ServerInteractionDemo.health.ConfigurationHealthCheck;
import org.junit.Before;
import org.junit.Test;


import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class NetworkServerApplicationTest {

    private final Bootstrap<NetworkServerConfiguration> bootstrap   = mock(Bootstrap.class);
    private final Environment                           environment = mock(Environment.class);
    private final JerseyEnvironment                     jersey      = mock(JerseyEnvironment.class);
    private final HealthCheckRegistry                   healthCheck = mock(HealthCheckRegistry.class);
    private final MetricRegistry                        metrics     = mock(MetricRegistry.class);
    private final NetworkServerApplication              app         = new NetworkServerApplication();
    private final NetworkServerConfiguration            config      = new NetworkServerConfiguration();

    @Before
    public void setup() throws Exception {
        this.config.setServer(new Object());
        this.config.setDealServerConfiguration(RetrofitConfiguration.builder().url("http://mock.url/").build());
        when(this.environment.healthChecks()).thenReturn(this.healthCheck);
        when(this.environment.metrics()).thenReturn(this.metrics);
        when(this.environment.jersey()).thenReturn(this.jersey);
    }

    @Test
    public void getName() { assertEquals("NetworkServer", this.app.getName());}

    @Test
    public void initialize() {
        this.app.initialize(this.bootstrap);
        verify(this.bootstrap).addBundle(isA(SwaggerBundle.class));
    }

    @Test
    public void run() {
        this.app.run(this.config, this.environment);
        verify(this.healthCheck).register(any(), isA(ConfigurationHealthCheck.class));
    }
}
