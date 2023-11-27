package com.example.testProjectW.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "m_movie")
public class Movie {

    @Id
    @Column(name = "row_id", length = 36, nullable = false, updatable = false)
    private String rowId;

    @Column(length = 50)
    private String movieAccntNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 36)
    private Date created;

    @Column(length = 50)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 36)
    private Date lastUpd;

    @Column(length = 50)
    private String lastUpdBy;

    @Column(name = "name", length = 50, nullable = false)
    private String movieTitle;

    @Column(name = "year_released", length = 50, nullable = false)
    private String yearReleased;

    @Column(name = "rating", length = 50, nullable = false)
    private String rating;

    @Column(name = "description", length = 255)
    private String description;


    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getMovieAccntNo() {
        return movieAccntNo;
    }

    public void setMovieAccntNo(String movieAccntNo) {
        this.movieAccntNo = movieAccntNo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getLastUpd() {
        return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
        this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
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

}
