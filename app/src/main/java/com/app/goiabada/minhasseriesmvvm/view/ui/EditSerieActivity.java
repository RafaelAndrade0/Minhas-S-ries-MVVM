package com.app.goiabada.minhasseriesmvvm.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.goiabada.minhasseriesmvvm.R;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;
import com.app.goiabada.minhasseriesmvvm.viewmodel.SerieViewModel;

public class EditSerieActivity extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private CheckBox checkBoxFavorito;
    private Button buttonAlterar;

    private SerieViewModel serieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_serie);

        serieViewModel = ViewModelProviders.of(this).get(SerieViewModel.class);

        editTextTitulo = findViewById(R.id.edit_text_titulo);
        editTextDescricao = findViewById(R.id.edit_text_descricao);
        checkBoxFavorito = findViewById(R.id.checkbox_favorito);
        buttonAlterar = findViewById(R.id.botao_alterar_serie);

        Intent intent = getIntent();
        editTextTitulo.setText(intent.getStringExtra("titulo"));
        editTextDescricao.setText(intent.getStringExtra("descricao"));
        checkBoxFavorito.setChecked(intent.getBooleanExtra("favorito", false));

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarSerie();
            }
        });
    }

    private void alterarSerie() {
        String titulo = editTextTitulo.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        boolean favorito = checkBoxFavorito.isChecked();

        int id = getIntent().getIntExtra("id", -1);

        Serie serie = new Serie(titulo, descricao, favorito);
        serie.setId(id);

        serieViewModel.alterar(serie);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_serie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite_serie:
                Toast.makeText(this, "Favorite!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
