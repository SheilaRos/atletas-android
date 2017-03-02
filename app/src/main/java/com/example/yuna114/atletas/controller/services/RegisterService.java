package com.example.yuna114.atletas.controller.services;
import com.example.yuna114.atletas.model.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("api/register")
    Call<Void> registerAccount(
            @Body UserDTO userDTO
    );
}