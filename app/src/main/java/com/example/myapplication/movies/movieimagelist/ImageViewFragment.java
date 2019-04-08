package com.example.myapplication.movies.movieimagelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.Movie;
import com.example.myapplication.moviedetail.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ImageViewFragment extends Fragment {

    public static ImageViewFragment newInstance(List<Movie> movies) {
        Bundle args = new Bundle();
        ArrayList<Movie> movieList = new ArrayList<>(movies);
        args.putParcelableArrayList(MOVIES_KEY, movieList);
        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String MOVIES_KEY = "movies";
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_view_fragment, container, false);

        if (view.findViewById(R.id.item_detail_container) != null) {
            isTwoPane = true;
        }

        RecyclerView recyclerView = view.findViewById(R.id.image_list_recycler);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ImageViewAdapter adapter = new ImageViewAdapter(getArguments().getParcelableArrayList(MOVIES_KEY),
                movie -> {
                    if (!isTwoPane) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(((ViewGroup) getView().getParent()).getId(), MovieDetailFragment.newInstance(movie))
                                .addToBackStack(null)
                                .commit();
                    } else {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.item_detail_container, MovieDetailFragment.newInstance(movie))
                                .addToBackStack(null)
                                .commit();
                    }
                });

        recyclerView.setAdapter(adapter);
        ((MainActivity) getActivity()).setCurrentFragment(this);
        return view;
    }
}
