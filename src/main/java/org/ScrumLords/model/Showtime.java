package org.ScrumLords.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a scheduled showing of a movie.
 * Stores the movie identifier and the start and end times
 * for a specific screening.
 *
 * @author A. Garcia
 * @version 1.0
 */
public class Showtime {
    private int showtimeId;
    private int movieId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a Showtime object.
     *
     * @param showtimeID unique identifier for the showtime
     * @param movieID identifier of the associated movie
     * @param startTime scheduled start time
     * @param endTime scheduled end time
     */
    public Showtime(int showtimeId, int movieId, LocalDateTime startTime, LocalDateTime endTime) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Calculates the duration of the showtime.
     *
     * @return the showtime duration in minutes
     */
    public int getShowtimeDuration() {
        Duration duration = Duration.between(getStartTime(),getEndTime());
        return (int)duration.toMinutes();
    }
}