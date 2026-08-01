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
import java.util.ArrayList;

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
        String searchText = searchField.getText();
        System.out.println("Searching for: " + searchText);
    }

    @FXML
    public void initialize() {
        movies = new ArrayList<>();
        movies.add(new Movie("1a", "Interstellar", "PG13", 169, null, "space exploration"));
        movies.add(new Movie("2a", "Dune", "PG-13", 155, null, "Science fiction adventure"));
        movies.add(new Movie("3a", "Minecraft", "PG", 101, null, "Fantasy adventure"));
        movies.add(new Movie("4a", "Batman", "PG-13", 176, null, "Superhero crime drama"));

        Platform.runLater(() -> returnButton.requestFocus());

        displayMovies();
    }

    private void displayMovies() {
        int index = 0;

        for (Movie movie : movies) {
            int column = index % 3;
            int row = index / 3;

            VBox movieContainer = new VBox();
            Label movieTitleLabel = new Label(movie.getTitle());
            movieContainer.getChildren().add(movieTitleLabel);
            movieGallery.add(movieContainer, column, row);

            index += 1;
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