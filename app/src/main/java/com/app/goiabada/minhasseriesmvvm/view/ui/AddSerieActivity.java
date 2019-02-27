package com.app.goiabada.minhasseriesmvvm.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.app.goiabada.minhasseriesmvvm.R;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;
import com.app.goiabada.minhasseriesmvvm.viewmodel.SerieViewModel;

public class AddSerieActivity extends AppCompatActivity {

    private SerieViewModel serieViewModel;

    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private CheckBox checkBoxFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_serie);

        editTextTitulo = findViewById(R.id.edit_text_titulo);
        editTextDescricao = findViewById(R.id.edit_text_descricao);
        checkBoxFavorito = findViewById(R.id.checkbox_favorito);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_serie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvar_serie:
                salvarSerie();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void salvarSerie() {
        String titulo = editTextTitulo.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        boolean favorito = checkBoxFavorito.isChecked();

        Serie serie = new Serie(titulo, descricao, favorito);
        serieViewModel.inserir(serie);

        finish();
    }
}
