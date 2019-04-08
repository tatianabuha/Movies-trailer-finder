package com.example.myapplication.movies.movielist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.Movie;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {
    public interface OnListItemClickListener {
        void onItemClick(Movie movie);
    }

    private List<Movie> movies;
    private OnListItemClickListener listener;

    public ListViewAdapter(List<Movie> movies, OnListItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_row, viewGroup, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int position) {
        listViewHolder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView titleTextView;

        public ListViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.movie_id);
            titleTextView = view.findViewById(R.id.movie_title);
        }

        public void bind(Movie movie, OnListItemClickListener listener) {
            idTextView.setText(movie.getId());
            titleTextView.setText(movie.getTitle());
            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
