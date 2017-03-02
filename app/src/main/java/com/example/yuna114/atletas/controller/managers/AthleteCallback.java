package com.example.yuna114.atletas.controller.managers;
import com.example.yuna114.atletas.model.Atleta;
import java.util.List;
public interface AthleteCallback {
    void onSuccess(List<Atleta> atletasList);
    void onSucces();
    void onSucces(Atleta atleta);
    void onFailure(Throwable t);
}
