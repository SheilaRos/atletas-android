package com.example.yuna114.atletas.controller.services;

import com.example.yuna114.atletas.model.Atleta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AthleteService {
    @GET("/api/atletas")
    Call<List<Atleta>> getAllAthletes(
            @Header("Authorization") String Authorization
    );

    @POST("api/atletas") // Se tiene que cambiar en un interfaz propia
    Call<Atleta> createAthlete(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);


    @PUT("api/atletas")
    Call<Atleta> updateAthlete(
            @Header("Authorization") String Authorization,
            @Body Atleta atleta);

    @DELETE("api/atletas/{id}")
    Call<Void> deleteAthlete(
            @Header("Authorization") String Authorization,
            @Path("id") Long id);


}
