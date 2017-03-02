package com.example.yuna114.atletas.controller.managers;

import android.util.Log;

import com.example.yuna114.atletas.controller.services.AthleteService;
import com.example.yuna114.atletas.model.Atleta;
import com.example.yuna114.atletas.util.CustomProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AthleteManager {
    private static AthleteManager ourInstance;
    private List<Atleta> atletas;
    private Retrofit retrofit;
    private AthleteService athleteService;

    private AthleteManager() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        athleteService = retrofit.create(AthleteService.class);
    }

    public static AthleteManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new AthleteManager();
        }

        return ourInstance;
    }


    public synchronized void getAllAthletes(final AthleteCallback athleteCallback) {
        Call<List<Atleta>> call = athleteService.getAllAthletes(UserLoginManager.getInstance().getBearerToken());

        call.enqueue(new Callback<List<Atleta>>() {
            @Override
            public void onResponse(Call<List<Atleta>> call, Response<List<Atleta>> response) {
                atletas = response.body();

                int code = response.code();

                if (response.isSuccess()) {
                    athleteCallback.onSuccess(atletas);
                } else {
                    athleteCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<Atleta>> call, Throwable t) {
                Log.e("AthleteManager->", "getAllAthletes()->ERROR: " + t);

                athleteCallback.onFailure(t);
            }
        });
    }

    public Atleta getAthlete(String id) {
        for (Atleta atleta : atletas) {
            if (id.equals(atleta.getId().toString())) {
                return atleta;
            }
        }

        return null;
    }

    /* POST - CREATE PLAYER */

    public synchronized void createAthlete(final AthleteCallback athleteCallback,Atleta atleta) {
        Call<Atleta> call = athleteService.createAthlete(UserLoginManager.getInstance().getBearerToken(), atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //playerCallback.onSuccess1(apuestas1x2);
                    Log.e("Atleta->", "createAthlete: OK" + 100);

                } else {
                    athleteCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("AthleteManager->", "createAthlete: " + t);
                athleteCallback.onFailure(t);
            }
        });
    }

    /* PUT - UPDATE Athlete */
    public synchronized void updateAthlete(final AthleteCallback athleteCallback, Atleta atleta) {
        Call <Atleta> call = athleteService.updateAthlete(UserLoginManager.getInstance().getBearerToken() ,atleta);
        call.enqueue(new Callback<Atleta>() {
            @Override
            public void onResponse(Call<Atleta> call, Response<Atleta> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Atleta->", "updateAthlete: OOK" + 100);

                } else {
                    athleteCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Atleta> call, Throwable t) {
                Log.e("PlayerManager->", "updatePlayer: " + t);

                athleteCallback.onFailure(t);
            }
        });
    }

    /* DELETE - DELETE PLAYER */
    public synchronized void deleteAthlete(final AthleteCallback athleteCallback, Long id) {
        Call <Void> call = athleteService.deleteAthlete(UserLoginManager.getInstance().getBearerToken() ,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("Player->", "Deleted: OK");

                } else {
                    athleteCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("AthleteManager->", "deleteAthlete: " + t);

                athleteCallback.onFailure(t);
            }
        });
    }

}
