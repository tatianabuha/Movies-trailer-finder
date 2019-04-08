package com.example.myapplication;

import android.databinding.Observable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.data.Movie;
import com.example.myapplication.moviedetail.MovieDetailFragment;
import com.example.myapplication.movies.movieimagelist.ImageViewFragment;
import com.example.myapplication.movies.movielist.ListViewFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MoviesController moviesController = new MoviesController();
    private boolean hasDialogShown = false;
    private Fragment currentFragment;
    private static final String FLAG = "flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        moviesController.fetchMovies();
        moviesController.movies.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                List<Movie> movieList = moviesController.movies.get();
                if (!hasDialogShown) {
                    hasDialogShown = true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setMessage(R.string.dialog_text)
                            .setPositiveButton(R.string.image_view, (dialog, which) -> replaceFragment(ImageViewFragment.newInstance(movieList)))
                            .setNegativeButton(R.string.list_view, (dialog, which) -> replaceFragment(ListViewFragment.newInstance(movieList)));

                    builder.create().show();
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (!(currentFragment instanceof MovieDetailFragment)) {
            finish();
        } else if (findViewById(R.id.item_detail_container) != null) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FLAG, hasDialogShown);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            hasDialogShown = savedInstanceState.getBoolean(FLAG);
        }
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }
}
