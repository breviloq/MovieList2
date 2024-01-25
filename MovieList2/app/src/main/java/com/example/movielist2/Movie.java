package com.example.movielist2;


import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private int releaseYear;
    private boolean watched;

    public Movie(String title, int releaseYear, boolean watched) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return getTitle() + " (" + getReleaseYear() + ")"; // Возвращает название фильма и год
    }
}
