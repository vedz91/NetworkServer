package org.ServerInteractionDemo.core.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient<T> {
    final Class<T> apiType;

    public RetrofitClient(final Class<T> apiType) {
        this.apiType = apiType;
    }

    public static <T> RetrofitClient<T> retrofitClient(final Class<T> apiType) {
        return new RetrofitClient<>(apiType);
    }

    public T get(final RetrofitConfiguration configuration) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(configuration.getUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(this.apiType);
    }
}
