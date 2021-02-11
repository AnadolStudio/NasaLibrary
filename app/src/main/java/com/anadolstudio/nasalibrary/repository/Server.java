package com.anadolstudio.nasalibrary.repository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    public static final String BASE_URL = "https://images-api.nasa.gov/";

    private static Server server;
    private static NasaApi serverApi;

    private Server() {
    }

    public static Server get() {
        if (server == null) {

            server = new Server();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            serverApi = retrofit.create(NasaApi.class);
        }
        return server;
    }

    public NasaApi getApi() {
        return serverApi;
    }
}
