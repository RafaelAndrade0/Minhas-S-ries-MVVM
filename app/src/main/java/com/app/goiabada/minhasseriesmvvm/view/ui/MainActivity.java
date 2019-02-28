package com.app.goiabada.minhasseriesmvvm.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.goiabada.minhasseriesmvvm.R;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;
import com.app.goiabada.minhasseriesmvvm.view.adapter.SerieAdapter;
import com.app.goiabada.minhasseriesmvvm.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;

    public static final int ADD_SERIE_REQUEST = 0;
    public static final int EDIT_SERIE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddSerie = findViewById(R.id.button_add_serie);
        buttonAddSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSerieActivity.class);
                startActivity(intent);
            }
        });

        final SerieAdapter adapter = new SerieAdapter();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.retornarTodasSeries().observe(this, new Observer<List<Serie>>() {
            @Override
            public void onChanged(List<Serie> series) {
                adapter.submitList(series);
            }
        });

        // Swipe para deletar os itens
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mainViewModel.deletar(adapter.getSerieAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new SerieAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Serie serie) {
                Intent intent = new Intent(MainActivity.this, EditSerieActivity.class);
                intent.putExtra("id", serie.getId());
                intent.putExtra("titulo", serie.getTitulo());
                intent.putExtra("descricao", serie.getDescricao());
                intent.putExtra("favorito", serie.isSerieFavorita());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deletar_tudo_item:
                mainViewModel.deletarTodasSeries();
                Toast.makeText(this, "Tudo deletado!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
