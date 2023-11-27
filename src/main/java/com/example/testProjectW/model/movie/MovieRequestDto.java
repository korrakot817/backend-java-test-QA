package com.example.testProjectW.model.movie;

import com.example.testProjectW.model.RelateParty;

public class MovieRequestDto {

    private String AccntNo;

    private String movieTitle;

    private String yearReleased;

    private String rating;

    private String description;

    private RelateParty relateParty;


    public String getAccntNo() {
        return AccntNo;
    }

    public void setAccntNo(String accntNo) {
        AccntNo = accntNo;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RelateParty getRelateParty() {
        return relateParty;
    }

    public void setRelateParty(RelateParty relateParty) {
        this.relateParty = relateParty;
    }
}
