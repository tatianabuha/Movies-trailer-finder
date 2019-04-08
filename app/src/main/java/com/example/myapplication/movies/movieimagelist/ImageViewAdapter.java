package com.example.myapplication.movies.movieimagelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.Movie;

import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ImageViewHolder> {
    public interface OnImageItemClickListener {
        void onItemClick(Movie movie);
    }

    private List<Movie> movies;
    private OnImageItemClickListener listener;
    private static final String BASE_URL = "https://image.tmdb.org/t/p/w500";

    public ImageViewAdapter(List<Movie> movies, OnImageItemClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_view_row, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewAdapter.ImageViewHolder imageViewHolder, int position) {
        imageViewHolder.bind(movies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
            textView = itemView.findViewById(R.id.movie_title);
        }

        public void bind(Movie movie, OnImageItemClickListener listener) {
            textView.setText(movie.getTitle());
            Glide.with(itemView.getContext())
                    .load(BASE_URL + movie.getImageUrl())
                    .placeholder(R.drawable.placeholderr)
                    .into(imageView);

            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
