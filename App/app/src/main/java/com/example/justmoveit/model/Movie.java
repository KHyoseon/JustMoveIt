package com.example.justmoveit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    private String country;
    private String director;
    private String actor;
    private String title;
    private String genre;
    private String summary;
    private String runningTime;
    private String img;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String rating;
    private String engTitle;
    private String ageLimit;
    private String releaseDate;
    private String totalCustomer;
    private String movieCode;
    private MoviePlayingInfo[] moviePlayingInfoList;

    public Movie(String country, String director, String actor, String title, String genre, String summary, String runningTime, String img, String img2, String img3, String img4, String img5, String img6, String rating, String engTitle, String ageLimit, String releaseDate, String totalCustomer, String movieCode, MoviePlayingInfo[] moviePlayingInfoList) {
        this.country = country;
        this.director = director;
        this.actor = actor;
        this.title = title;
        this.genre = genre;
        this.summary = summary;
        this.runningTime = runningTime;
        this.img = img;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        this.rating = rating;
        this.engTitle = engTitle;
        this.ageLimit = ageLimit;
        this.releaseDate = releaseDate;
        this.totalCustomer = totalCustomer;
        this.movieCode = movieCode;
        this.moviePlayingInfoList = moviePlayingInfoList;
    }
    public String getMovieId() {
        return moviePlayingInfoList[0].getMovieId()+"";
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getImg6() {
        return img6;
    }

    public void setImg6(String img6) {
        this.img6 = img6;
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

    public MoviePlayingInfo[] getMoviePlayingInfoList() {
        return moviePlayingInfoList;
    }

    public MoviePlayingInfo getMoviePlayingInfoByIndex(int index){
        return moviePlayingInfoList[index];
    }

    public void setMoviePlayingInfoList(MoviePlayingInfo[] moviePlayingInfoList) {
        this.moviePlayingInfoList = moviePlayingInfoList;
    }
}
