package com.lambda.pe.lambdaapp.remote;

import com.lambda.pe.lambdaapp.config.WsConfig;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Service
public class ServiceGenerator {
    private final OkHttpClient httpClient;
    private final Retrofit.Builder builder;

    public ServiceGenerator(WsConfig wsConfig) {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .build();
        this.builder = new Retrofit.Builder()
                .baseUrl(wsConfig.getWsRoot())
                .addConverterFactory(GsonConverterFactory.create());
    }
    public  <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
