package com.anadolstudio.nasalibrary.repository;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NasaApi {

    @GET("search")
    Single<NasaItemResponse> getCollection(
            @Query("q") String query,
            @Query("page") int page,
            @Query("media_type") String mediaType);

    @GET("asset/{id}")
    Single<NasaImageResponse> getNasaImageMedia(@Path("id") String id);
}
