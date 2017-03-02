package com.example.yuna114.atletas.controller.managers;
import com.example.yuna114.atletas.model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}