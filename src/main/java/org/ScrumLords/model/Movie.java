package org.ScrumLords.model;

import java.util.List;

public class Movie {
    private String movieId;
    private String title;
    private String rating;
    private int runtime;
    private List<String> genres;
    private String description;
    private String posterPath;
    private List<Showtime> showtimes;

    public Movie(String movieId, String title, String rating, int runtime, List<String> genres, String description, String posterPath, List<Showtime> showtimes) {
        this.movieId = movieId;
        this.title = title;
        this.rating = rating;
        this.runtime = runtime;
        this.description = description;
        this.genres = genres;
        this.posterPath = posterPath;
        this.showtimes = showtimes;
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

    public List<Showtime> getShowtimes() {
        return showtimes;
    }
}