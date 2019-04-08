package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovieList() {
        return movies;
    }
}
