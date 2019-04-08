package com.example.myapplication.util;

import com.example.myapplication.data.MovieList;

import io.reactivex.Single;

import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("movie/popular")
    Single<MovieList> getMovies();
}
