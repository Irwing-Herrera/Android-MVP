package com.iherrera.androidmvp.http.interfaces;

import com.iherrera.androidmvp.http.models.Twitch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TwitchAPI {

    @GET("games/top")
    Call<Twitch> getTopGames(@Header("Authorization") String authorization, @Header("Client-Id") String clientId);

    @GET("games")
    Call<Twitch> getGames(@Query("id") int id, @Header("Authorization") String authorization, @Header("Client-Id") String clientId);
}
