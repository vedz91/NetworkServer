package org.ServerInteractionDemo;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.ServerInteractionDemo.core.retrofit.RetrofitConfiguration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NetworkServerConfiguration extends Configuration {
    Object server;

    @JsonProperty("swagger")
    SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("dealServerConfiguration")
    RetrofitConfiguration dealServerConfiguration;
}
