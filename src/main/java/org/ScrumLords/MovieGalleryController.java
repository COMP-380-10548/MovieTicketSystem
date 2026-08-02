package org.ScrumLords;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;
import javafx.geometry.HPos;

public class MovieGalleryController {

    private List<Movie> movies;

    @FXML
    private Button returnButton;

    @FXML
    private TextField searchField;

    @FXML
    private GridPane movieGallery;

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        List<Movie> filteredMovies = new ArrayList<>();
        System.out.println("Searching for: " + searchText);

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

    @FXML
    public void initialize() {
        movies = new ArrayList<>();

        List<Showtime> interstellarShowtimes = List.of(
            new Showtime(
                1,       // showtime ID
                1,          // movie ID (Interstellar)
                LocalDateTime.of(2026, 8, 2, 18, 30),  // 6:30 PM
                LocalDateTime.of(2026, 8, 2, 21, 19)   // End time
            ),
            new Showtime(
                2,
                1,
                LocalDateTime.of(2026, 8, 2, 22, 15),  // 10:15 PM
                LocalDateTime.of(2026, 8, 3, 1, 4)     // Ends after midnight
            )
        );

        List<Showtime> duneShowtimes = List.of(
            new Showtime(
                3,
                2,
                LocalDateTime.of(2026, 8, 2, 13, 0),
                LocalDateTime.of(2026, 8, 2, 15, 35)
            ),
            new Showtime(
                4,
                2,
                LocalDateTime.of(2026, 8, 2, 16, 45),
                LocalDateTime.of(2026, 8, 2, 19, 20)
            ),
            new Showtime(
                5,
                2,
                LocalDateTime.of(2026, 8, 2, 20, 30),
                LocalDateTime.of(2026, 8, 2, 23, 5)
            )
        );

        List<Showtime> minecraftShowtimes = List.of(
            new Showtime(
                6,
                3,
                LocalDateTime.of(2026, 8, 2, 12, 0),
                LocalDateTime.of(2026, 8, 2, 13, 41)
            ),
            new Showtime(
                7,
                3,
                LocalDateTime.of(2026, 8, 2, 14, 30),
                LocalDateTime.of(2026, 8, 2, 16, 11)
            )
        );

        List<Showtime> batmanShowtimes = List.of(
            new Showtime(
                8,
                4,
                LocalDateTime.of(2026, 8, 2, 19, 0),
                LocalDateTime.of(2026, 8, 2, 21, 56)
            ),
            new Showtime(
                9,
                4,
                LocalDateTime.of(2026, 8, 2, 21, 45),
                LocalDateTime.of(2026, 8, 3, 0, 41)
            )
        );

        movies.add(new Movie("1a", "Interstellar", "PG13", 169, null, "space exploration","/org/ScrumLords/images/interstellar.jpg", interstellarShowtimes));
        movies.add(new Movie("2a", "Dune", "PG-13", 155, null, "Science fiction adventure","/org/ScrumLords/images/dune.jpg", duneShowtimes));
        movies.add(new Movie("3a", "Minecraft", "PG", 101, null, "Fantasy adventure","/org/ScrumLords/images/minecraft.jpg", minecraftShowtimes));
        movies.add(new Movie("4a", "Batman", "PG-13", 176, null, "Superhero crime drama","/org/ScrumLords/images/batman.jpg", batmanShowtimes));

        Platform.runLater(() -> returnButton.requestFocus());

        displayMovies(movies);
    }

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
                SceneManager.<MovieDetailsController>switchToScene("MovieDetails.fxml", 
                controller -> {
                    controller.setMovie(movie);
                });
            });
        }
    }

    // TODO: use setUserData to associate each movie item with a specific movie
    public void viewMovieDetails(ActionEvent e) {
       SceneManager.switchToScene("MovieDetails.fxml", null); 
    }

    public void returnToMainPage(ActionEvent e) {
        SceneManager.switchToScene("MainPage.fxml", null);
    }
}