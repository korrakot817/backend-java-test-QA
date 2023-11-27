package com.example.testProjectW.model.movie;

public class MovieResponseDto {

    private String movieAccntNo;

    private String movieTitle;

    private String rating;


    public String getMovieAccntNo() {
        return movieAccntNo;
    }

    public void setMovieAccntNo(String movieAccntNo) {
        this.movieAccntNo = movieAccntNo;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
