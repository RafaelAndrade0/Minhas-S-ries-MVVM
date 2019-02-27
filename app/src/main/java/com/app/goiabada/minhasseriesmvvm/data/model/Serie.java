package com.app.goiabada.minhasseriesmvvm.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "serie_table")
public class Serie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String descricao;
    private boolean serieFavorita;

    public Serie(String titulo, String descricao, boolean serieFavorita) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.serieFavorita = serieFavorita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isSerieFavorita() {
        return serieFavorita;
    }
}
