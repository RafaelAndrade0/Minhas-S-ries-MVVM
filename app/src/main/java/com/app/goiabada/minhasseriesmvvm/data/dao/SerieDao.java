package com.app.goiabada.minhasseriesmvvm.data.dao;

import com.app.goiabada.minhasseriesmvvm.data.model.Serie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SerieDao {

    @Insert
    void inserir(Serie serie);

    @Update
    void alterar(Serie serie);

    @Delete
    void deletar(Serie serie);

    @Query("DELETE FROM serie_table")
    void deletarTodasSeries();

    @Query("SELECT * FROM serie_table ORDER BY titulo ASC")
    LiveData<List<Serie>> retornarTodasSeries();
}
