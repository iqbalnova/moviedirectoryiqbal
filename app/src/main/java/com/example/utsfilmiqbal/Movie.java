package com.example.utsfilmiqbal;

public class Movie {
    private String title , poster , release, overview , backdrop ;
    private Double rating;

    public Movie(String title, String poster, String backdrop, String release,String overview, Double rating) {
        this.title = title;
        this.poster = poster;
        this.backdrop = backdrop;
        this.release = release;
        this.overview = overview;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getRelease() {
        return release;
    }

    public String getOverview() {
        return overview;
    }

    public Double getRating() {
        return rating;
    }
}
