package com.example.justmoveit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private Long id;
    private String country;
    private String director;
    private String actor;
    private String title;
    private String genre;
    private String summary;
    private String runningTime;
    private String mainImg;
    private List<String> subImgs;
    private String rating;
    private String engTitle;
    private String ageLimit;
    private String releaseDate;
    private String totalCustomer;
    private String movieCode;

    public Movie(Long id, String country, String director, String actor, String title, String genre, String summary, String runningTime, String mainImg, String img1, String img2, String img3, String img4, String img5, String rating, String engTitle, String ageLimit, String releaseDate, String totalCustomer, String movieCode) {
        this.id = id;
        this.country = country;
        this.director = director;
        this.actor = actor;
        this.title = title;
        this.genre = genre;
        this.summary = summary;
        this.runningTime = runningTime;
        this.mainImg = mainImg;

        this.subImgs = new ArrayList<>(5);
        this.subImgs.add(img1);
        this.subImgs.add(img2);
        this.subImgs.add(img3);
        this.subImgs.add(img4);
        this.subImgs.add(img5);

        this.rating = rating;
        this.engTitle = engTitle;
        this.ageLimit = ageLimit;
        this.releaseDate = releaseDate;
        this.totalCustomer = totalCustomer;
        this.movieCode = movieCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public List<String> getSubImgs() {
        return subImgs;
    }

    public void setSubImgs(List<String> subImgs) {
        this.subImgs = subImgs;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(String totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }
}
