package com.example.myapplication;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.myapplication.data.Movie;
import com.example.myapplication.data.MovieList;
import com.example.myapplication.util.MoviesApi;
import com.example.myapplication.util.NetworkInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MoviesController extends ViewModel {
    public ObservableField<List<Movie>> movies = new ObservableField<>();

    @SuppressLint("CheckResult")
    public void fetchMovies() {
        MoviesApi moviesApi = new MoviesApi();
        NetworkInterface networkInterface = moviesApi.getClient();
        networkInterface.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<MovieList>() {
                                   @Override
                                   public void onSuccess(MovieList movieList) {
                                       movies.set(movieList.getMovieList());
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       Log.d("TATI", e.getMessage());
                                   }
                               }
                );
    }
}
