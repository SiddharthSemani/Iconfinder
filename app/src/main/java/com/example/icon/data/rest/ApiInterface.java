package com.example.icon.data.rest;


import com.example.icon.data.model.Icon;
import com.example.icon.data.model.IconsetObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"authorization: Bearer eaIMP2v9b3GoZYytS4jWHtuBA1W75TD2cvEj91I9hKGxUAtqkK1ZuqfO4gm0wVxx"})
    @GET("iconsets")
    Call<IconsetObject>getIconsetsByquery();

    @Headers({"authorization: Bearer eaIMP2v9b3GoZYytS4jWHtuBA1W75TD2cvEj91I9hKGxUAtqkK1ZuqfO4gm0wVxx"})
    @GET("iconsets/{id}/icons")
    Call<Icon> getIconsFromIconset(@Path("id")String iconsetId);


    @Headers({"authorization: Bearer eaIMP2v9b3GoZYytS4jWHtuBA1W75TD2cvEj91I9hKGxUAtqkK1ZuqfO4gm0wVxx"})
    @GET("icons/search")
    Call<Icon> getIconsByQuery(@Query("query") String query,
                               @Query("count") int count,
                               @Query("offset") int offset);
}

