package org.ServerInteractionDemo;

import org.ServerInteractionDemo.api.exception.mapper.DataServiceExceptionMapper;
import org.ServerInteractionDemo.api.exception.mapper.InvalidOperandExceptionMapper;
import org.ServerInteractionDemo.client.DataServiceClient;
import org.ServerInteractionDemo.core.retrofit.RetrofitClient;
import org.ServerInteractionDemo.health.ConfigurationHealthCheck;
import org.ServerInteractionDemo.resources.OperationResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import static org.ServerInteractionDemo.core.retrofit.RetrofitClient.retrofitClient;

public class NetworkServerApplication extends Application<NetworkServerConfiguration> {

    private final RetrofitClient<DataServiceClient> dataServiceClient = retrofitClient(DataServiceClient.class);

    public static void main(final String[] args) throws Exception {
        new NetworkServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "NetworkServer";
    }

    @Override
    public void initialize(final Bootstrap<NetworkServerConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(new SwaggerBundle<NetworkServerConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final NetworkServerConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final NetworkServerConfiguration configuration,
                    final Environment env) {
        // HEALTH Check
        env.healthChecks()
           .register("ConfigurationHealthCheck",
                     ConfigurationHealthCheck.builder().serverConfiguration(new Object()).build());

        // EXCEPTION MAPPERS
        env.jersey().register(new InvalidOperandExceptionMapper(env.metrics()));
        env.jersey().register(new DataServiceExceptionMapper(env.metrics()));

        // RESOURCES
        env.jersey()
           .register(OperationResource.builder()
                                      .dataServiceClient(this.dataServiceClient.get(configuration.dealServerConfiguration))
                                      .build());
    }

}
