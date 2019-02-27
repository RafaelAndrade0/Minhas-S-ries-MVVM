package com.app.goiabada.minhasseriesmvvm.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.goiabada.minhasseriesmvvm.R;

public class EditSerieActivity extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private CheckBox checkBoxFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_serie);

        editTextTitulo = findViewById(R.id.edit_text_titulo);
        editTextDescricao = findViewById(R.id.edit_text_descricao);
        checkBoxFavorito = findViewById(R.id.checkbox_favorito);

        Intent intent = getIntent();
        editTextTitulo.setText(intent.getStringExtra("titulo"));
        editTextDescricao.setText(intent.getStringExtra("descricao"));
        checkBoxFavorito.setChecked(intent.getBooleanExtra("favorito", false));
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
