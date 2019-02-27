package com.app.goiabada.minhasseriesmvvm.viewmodel;

import android.app.Application;

import com.app.goiabada.minhasseriesmvvm.data.model.Serie;
import com.app.goiabada.minhasseriesmvvm.data.repository.SerieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SerieViewModel extends AndroidViewModel {

    private SerieRepository repository;

    public SerieViewModel(@NonNull Application application) {
        super(application);
        repository = new SerieRepository(application);
    }

    public void inserir(Serie serie) {
        repository.inserir(serie);
    }

    public void alterar(Serie serie) {
        repository.alterar(serie);
    }

}
