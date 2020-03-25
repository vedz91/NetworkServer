package org.ServerInteractionDemo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NetworkServerApplication extends Application<NetworkServerConfiguration> {

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
    }

    @Override
    public void run(final NetworkServerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
