package org.ScrumLords.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

import org.ScrumLords.SceneManager;
import org.ScrumLords.model.Movie;
import org.ScrumLords.model.Showtime;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.geometry.HPos;

/**
 * Controls the Movie Gallery view.
 * Displays available movies, supports title and description searches,
 * and opens the details page for a selected movie.
 *
 * @author A. Garcia
 * @version 1.0
 * @since 2026-06-28
 */
public class MovieGalleryController {

    private List<Movie> movies;

    @FXML
    private Button backButton;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane movieGallery;

    @FXML
    private ImageView logoImage;

    private javafx.scene.image.Image logo;

    /**
     * Filters the displayed movies using the text entered in the search field.
     * Movies are matched by title or description.
     *
     * @param event the search button event
     */
    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        List<Movie> filteredMovies = new ArrayList<>();
        //System.out.println("Searching for: " + searchText);

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(searchText)) 
                filteredMovies.add(movie);
            else if (movie.getDescription().toLowerCase().contains(searchText))
                filteredMovies.add(movie);
        }

        if (filteredMovies.isEmpty()) {
            movieGallery.getChildren().clear();
            Label noResultsLabel = new Label("No movies match your search.");
            movieGallery.add(noResultsLabel, 0, 0, 3, 1);
            GridPane.setHalignment(noResultsLabel, HPos.CENTER);
        }
        else {
            displayMovies(filteredMovies);
        }  
    }

    /**
     * Initializes the movie gallery with temporary demo movie and showtime data.
     * This data will be replaced with database-backed movie data.
     */
    @FXML
    public void initialize() {
        movies = new ArrayList<>();

    List<Showtime> interstellarShowtimes = List.of(
        new Showtime(
            1,
            1,
            LocalDateTime.of(2026, 8, 13, 18, 30),
            LocalDateTime.of(2026, 8, 13, 21, 19)
        ),
        new Showtime(
            2,
            1,
            LocalDateTime.of(2026, 8, 13, 22, 15),
            LocalDateTime.of(2026, 8, 14, 1, 4)
        )
    );

    List<Showtime> duneShowtimes = List.of(
        new Showtime(
            3,
            2,
            LocalDateTime.of(2026, 8, 13, 13, 0),
            LocalDateTime.of(2026, 8, 13, 15, 35)
        ),
        new Showtime(
            4,
            2,
            LocalDateTime.of(2026, 8, 13, 16, 45),
            LocalDateTime.of(2026, 8, 13, 19, 20)
        ),
        new Showtime(
            5,
            2,
            LocalDateTime.of(2026, 8, 13, 20, 30),
            LocalDateTime.of(2026, 8, 13, 23, 5)
        )
    );

    List<Showtime> minecraftShowtimes = List.of(
        new Showtime(
            6,
            3,
            LocalDateTime.of(2026, 8, 13, 12, 0),
            LocalDateTime.of(2026, 8, 13, 13, 41)
        ),
        new Showtime(
            7,
            3,
            LocalDateTime.of(2026, 8, 14, 14, 30),
            LocalDateTime.of(2026, 8, 14, 16, 11)
        )
    );

    List<Showtime> batmanShowtimes = List.of(
        new Showtime(
            8,
            4,
            LocalDateTime.of(2026, 8, 13, 19, 0),
            LocalDateTime.of(2026, 8, 13, 21, 56)
        ),
        new Showtime(
            9,
            4,
            LocalDateTime.of(2026, 8, 13, 22, 15),
            LocalDateTime.of(2026, 8, 14, 1, 11)
        )
    );

    List<Showtime> oppenheimerShowtimes = List.of(
        new Showtime(
            10,
            5,
            LocalDateTime.of(2026, 8, 15, 13, 0),
            LocalDateTime.of(2026, 8, 15, 16, 0)
        ),
        new Showtime(
            11,
            5,
            LocalDateTime.of(2026, 8, 15, 18, 30),
            LocalDateTime.of(2026, 8, 15, 21, 30)
        )
    );

    List<Showtime> marioShowtimes = List.of(
        new Showtime(
            12,
            6,
            LocalDateTime.of(2026, 8, 15, 12, 30),
            LocalDateTime.of(2026, 8, 15, 14, 2)
        ),
        new Showtime(
            13,
            6,
            LocalDateTime.of(2026, 8, 15, 15, 30),
            LocalDateTime.of(2026, 8, 15, 17, 2)
        )
    );

    List<Showtime> spiderManShowtimes = List.of(
        new Showtime(
            14,
            7,
            LocalDateTime.of(2026, 8, 15, 14, 0),
            LocalDateTime.of(2026, 8, 15, 16, 20)
        ),
        new Showtime(
            15,
            7,
            LocalDateTime.of(2026, 8, 15, 19, 0),
            LocalDateTime.of(2026, 8, 15, 21, 20)
        )
    );

    List<Showtime> topGunShowtimes = List.of(
        new Showtime(
            16,
            8,
            LocalDateTime.of(2026, 8, 15, 16, 0),
            LocalDateTime.of(2026, 8, 15, 18, 11)
        ),
        new Showtime(
            17,
            8,
            LocalDateTime.of(2026, 8, 15, 20, 0),
            LocalDateTime.of(2026, 8, 15, 22, 11)
        )
    );

        movies.add(new Movie(
            "1a",
            "Interstellar",
            "PG13", 
            169, 
            null, 
            "space exploration",
            "/org/ScrumLords/images/interstellar.jpg", 
            interstellarShowtimes));

        movies.add(new Movie(
            "2a", 
            "Dune", 
            "PG-13", 
            155, 
            null, 
            "Science fiction adventure",
            "/org/ScrumLords/images/dune.jpg", 
            duneShowtimes
        ));

        movies.add(new Movie(
            "3a",
            "Minecraft",
            "PG", 
            101, 
            null, 
            "Fantasy adventure",
            "/org/ScrumLords/images/minecraft.jpg", 
            minecraftShowtimes
        ));

        movies.add(new Movie(
            "4a",
            "Batman",
            "PG-13",
            176,
            null,
            "Superhero crime drama",
            "/org/ScrumLords/images/batman.jpg",
            batmanShowtimes
        ));

        movies.add(new Movie(
            "5a",
            "Oppenheimer",
            "R",
            180,
            null,
            "Historical drama about the development of the atomic bomb",
            "/org/ScrumLords/images/oppenheimer.jpg",
            oppenheimerShowtimes
        ));

        movies.add(new Movie(
            "6a",
            "The Super Mario Bros. Movie",
            "PG",
            92,
            null,
            "Animated adventure through the Mushroom Kingdom",
            "/org/ScrumLords/images/mario.jpg",
            marioShowtimes
        ));

        movies.add(new Movie(
            "7a",
            "Spider-Man: Across the Spider-Verse",
            "PG",
            140,
            null,
            "Animated superhero adventure across the multiverse",
            "/org/ScrumLords/images/spiderman.jpg",
            spiderManShowtimes
        ));

        movies.add(new Movie(
            "8a",
            "Top Gun: Maverick",
            "PG-13",
            131,
            null,
            "Action drama about an elite group of Navy fighter pilots",
            "/org/ScrumLords/images/topgun.jpg",
            topGunShowtimes
        ));

        Platform.runLater(() -> backButton.requestFocus());

        displayMovies(movies);
    }

    /**
     * Returns the user to the Main Page when the logo is clicked.
     *
     * @param event the logo click event
     */
    @FXML
    public void handleLogoClick(MouseEvent event) {
        SceneManager.switchToScene("MainPage", null);
    }

    /**
     * Clears and repopulates the gallery with the provided movies.
     * Each movie is displayed with its poster and title and can be selected
     * to open the Movie Details view.
     *
     * @param moviesToDisplay movies to display in the gallery
     */
    private void displayMovies(List<Movie> moviesToDisplay) {
        int index = 0;

        movieGallery.getChildren().clear();

        for (Movie movie : moviesToDisplay) {
            int column = index % 3;
            int row = index / 3;

            VBox movieContainer = new VBox();
            movieContainer.setPrefWidth(240);
            movieContainer.setPrefHeight(300);
            movieContainer.setSpacing(8);
            movieContainer.setAlignment(Pos.CENTER);
            
            ImageView movieImage = new ImageView();
            Image poster = new Image(getClass().getResource(movie.getPosterPath()).toExternalForm());
            movieImage.setImage(poster);
            movieImage.setFitWidth(240);
            movieImage.setPreserveRatio(true);

            Label movieTitleLabel = new Label(movie.getTitle());

            movieContainer.getChildren().add(movieImage);
            movieContainer.getChildren().add(movieTitleLabel);
            movieGallery.add(movieContainer, column, row);

            index += 1;

            movieContainer.setOnMouseClicked(event -> {
                SceneManager.<MovieDetailsController>switchToScene("MovieDetails", 
                controller -> {
                    controller.setMovie(movie);
                });
            });
        }
    }

    // TODO: use setUserData to associate each movie item with a specific movie

    /**
     * Returns the user to the Main Page.
     *
     * @param event the back button event
     */
    @FXML
    public void goBack(ActionEvent event) {
        SceneManager.switchToScene(
            "MainPage",
            null
        );
    }
}