package org.ScrumLords;

public class Movie {
    private String title;
    private String rating;
    private String runtime;
    private String description;

    public Movie(String title, String rating, String runtime, String description) {
        this.title = title;
        this.rating = rating;
        this.runtime = runtime;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDescription() {
        return description;
    }
}


