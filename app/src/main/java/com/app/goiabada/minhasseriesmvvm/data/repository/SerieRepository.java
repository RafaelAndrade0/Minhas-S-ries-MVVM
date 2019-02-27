package com.app.goiabada.minhasseriesmvvm.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.app.goiabada.minhasseriesmvvm.data.SerieDatabase;
import com.app.goiabada.minhasseriesmvvm.data.dao.SerieDao;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SerieRepository {
    private SerieDao serieDao;
    private LiveData<List<Serie>> todasSeries;

    public SerieRepository(Application application) {
        SerieDatabase serieDatabase = SerieDatabase.getInstance(application);
        serieDao = serieDatabase.serieDao();
        todasSeries = serieDao.retornarTodasSeries();
    }

    public void inserir(Serie serie) {
        new InserirSerieAsyncTask(serieDao).execute(serie);
    }

    public void alterar(Serie serie) {

    }

    public void deletar(Serie serie) {
        new DeletarSerieAsyncTask(serieDao).execute(serie);
    }

    public void deletarTodasSeries() {
        new DeletarTodasSeriesAsyncTask(serieDao).execute();
    }

    public LiveData<List<Serie>> retornarTodasSeries() {
        return todasSeries;
    }

    private static class InserirSerieAsyncTask extends AsyncTask<Serie, Void, Void> {

        private SerieDao serieDao;

        private InserirSerieAsyncTask(SerieDao serieDao) {
            this.serieDao = serieDao;
        }


        @Override
        protected Void doInBackground(Serie... series) {
            serieDao.inserir(series[0]);
            return null;
        }
    }

    private static class DeletarSerieAsyncTask extends AsyncTask<Serie, Void, Void>{

        private SerieDao serieDao;

        private DeletarSerieAsyncTask(SerieDao serieDao) {
            this.serieDao = serieDao;
        }

        @Override
        protected Void doInBackground(Serie... notes) {
            serieDao.deletar(notes[0]);
            return null;
        }
    }

    private static class DeletarTodasSeriesAsyncTask extends AsyncTask<Void, Void, Void>{

        private SerieDao serieDao;

        private DeletarTodasSeriesAsyncTask(SerieDao serieDao) {
            this.serieDao = serieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            serieDao.deletarTodasSeries();
            return null;
        }
    }
}
