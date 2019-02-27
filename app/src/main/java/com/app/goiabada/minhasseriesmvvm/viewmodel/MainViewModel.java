package com.app.goiabada.minhasseriesmvvm.viewmodel;

import android.app.Application;

import com.app.goiabada.minhasseriesmvvm.data.model.Serie;
import com.app.goiabada.minhasseriesmvvm.data.repository.SerieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {
    private SerieRepository repository;
    private LiveData<List<Serie>> todasSeries;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new SerieRepository(application);
        todasSeries = repository.retornarTodasSeries();
    }

    public void deletar(Serie serie) {
        repository.deletar(serie);
    }

    public void deletarTodasSeries() {
        repository.deletarTodasSeries();
    }

    public LiveData<List<Serie>> retornarTodasSeries() {
        return todasSeries;
    }
}
