package com.app.goiabada.minhasseriesmvvm.data;

import android.content.Context;
import android.os.AsyncTask;

import com.app.goiabada.minhasseriesmvvm.data.dao.SerieDao;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Serie.class}, version = 1)
public abstract class SerieDatabase extends RoomDatabase {
    private static SerieDatabase instance;

    public abstract SerieDao serieDao();

    // Quando a instancia for criada pela primeira vez o roomCallback é chamado
    public static synchronized SerieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SerieDatabase.class, "serie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SerieDao serieDao;

        public PopulateDbAsyncTask(SerieDatabase serieDatabase) {
            serieDao = serieDatabase.serieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            serieDao.inserir(new Serie("Serie 1", "Descricao 1", false));
            serieDao.inserir(new Serie("Serie 2", "Descricao 2", true));
            serieDao.inserir(new Serie("Serie 3", "Descricao 3", false));
            return null;
        }
    }
}


