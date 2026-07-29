package org.ScrumLords;
import java.time.Duration;
import java.time.LocalDateTime;

public class Showtime {
    private int showtimeID;
    private int movieID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Showtime(int showtimeID, int movieID, LocalDateTime startTime, LocalDateTime endTime) {
        this.showtimeID = showtimeID;
        this.movieID = movieID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getShowtimeID() {
        return showtimeID;
    }

    public int getMovieID() {
        return movieID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getShowtimeDuration() {
        Duration duration = Duration.between(getStartTime(),getEndTime());
        return (int)duration.toMinutes();
    }
}