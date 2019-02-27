package com.app.goiabada.minhasseriesmvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.goiabada.minhasseriesmvvm.R;
import com.app.goiabada.minhasseriesmvvm.data.model.Serie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SerieAdapter extends ListAdapter<Serie, SerieAdapter.SerieHolder> {

    private OnItemClickListener listener;

    public SerieAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Serie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Serie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Serie oldItem, @NonNull Serie newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Serie oldItem, @NonNull Serie newItem) {
            return oldItem.getDescricao().equals(newItem.getDescricao()) &&
                    oldItem.getTitulo().equals(newItem.getTitulo()) &&
                    oldItem.isSerieFavorita() == newItem.isSerieFavorita();
        }
    };

    @NonNull
    @Override
    public SerieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serie_item, parent, false);
        return new SerieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SerieHolder holder, int position) {
        Serie serie = getItem(position);
        holder.textViewTitulo.setText(serie.getTitulo());
        holder.textViewDescricao.setText(serie.getDescricao());
        if (serie.isSerieFavorita()) {
            holder.imageViewFavorito.setImageResource(R.drawable.ic_favorite_red_24dp);
        } else {
            holder.imageViewFavorito.setImageResource(R.drawable.ic_favorite_border_red_24dp);
        }
    }

    public Serie getSerieAt(int position) {
        return getItem(position);
    }

    class SerieHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitulo;
        private TextView textViewDescricao;
        private ImageView imageViewFavorito;

        public SerieHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.text_titulo);
            textViewDescricao = itemView.findViewById(R.id.text_view_description);
            imageViewFavorito = itemView.findViewById(R.id.favorite_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Serie serie);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
