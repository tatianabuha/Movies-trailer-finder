package com.example.myapplication.moviedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.moviedetail.movietrailer.YoutubePlayerActivity;
import com.example.myapplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailFragment extends Fragment {

    public static MovieDetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w500";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);
        ((MainActivity) getActivity()).setCurrentFragment(this);

        Movie movie = getArguments().getParcelable("movie");

        List<String> videoUrls = new ArrayList<>();

        videoUrls.add("xRc3WviXk2M");
        videoUrls.add("1tzFRIQfwXg");
        videoUrls.add("-oD7B7oiBtw");
        videoUrls.add("6dSKUoV0SNI");

        TextView idTextView = view.findViewById(R.id.movie_id);
        idTextView.setText(movie.getId());

        TextView titleTextView = view.findViewById(R.id.movie_title);
        titleTextView.setText(movie.getTitle());

        ImageView imageView = view.findViewById(R.id.movie_image);
        Glide.with(getContext())
                .load(BASE_URL + movie.getImageUrl())
                .into(imageView);

        RecyclerView recyclerView = view.findViewById(R.id.trailer_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.column_number)));

        YoutubeThumbnailAdapter youtubeThumbnailAdapter = new YoutubeThumbnailAdapter(videoUrls, (videoUrl) -> startActivity(YoutubePlayerActivity.startIntent(videoUrl, getContext())));
        recyclerView.setAdapter(youtubeThumbnailAdapter);
        return view;
    }
}
