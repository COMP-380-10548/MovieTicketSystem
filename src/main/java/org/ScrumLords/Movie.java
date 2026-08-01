package org.ScrumLords;

import java.util.List;

public class Movie {
    private String movieId;
    private String title;
    private String rating;
    private int runtime;
    private List<String> genres;
    private String description;
    private String posterPath;

    public Movie(String movieId, String title, String rating, int runtime, List<String> genres, String description, String posterPath) {
        this.movieId = movieId;
        this.title = title;
        this.rating = rating;
        this.runtime = runtime;
        this.description = description;
        this.genres = genres;
        this.posterPath = posterPath;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterPath() {
        return posterPath;
    }
}