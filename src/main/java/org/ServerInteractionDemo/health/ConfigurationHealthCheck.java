package org.ServerInteractionDemo.health;

import com.codahale.metrics.health.HealthCheck;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConfigurationHealthCheck extends HealthCheck {
    Object serverConfiguration;

    @Override
    protected Result check() throws Exception {
        return this.serverConfiguration != null ? Result.healthy() : Result.unhealthy("No Server Configuration");
    }
}
