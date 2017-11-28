package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 28/11/17.
 */

public class SearchResultItem {

    public SearchResultItem(String rush_id, String title, String author, String rating, String est_time, String pages, Object audio, Object video, String description, String cover, String genre, String attr, Object url, String cost, String type, String datetime) {
        this.rush_id = rush_id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.est_time = est_time;
        this.pages = pages;
        this.audio = audio;
        this.video = video;
        this.description = description;
        this.cover = cover;
        this.genre = genre;
        this.attr = attr;
        this.url = url;
        this.cost = cost;
        this.type = type;
        this.datetime = datetime;
    }

    /**
     * rush_id : 1
     * title : The monk who sold his Ferrari
     * author : Robin Sharma
     * rating : 3.8
     * est_time : 11
     * pages : 10
     * audio : null
     * video : null
     * description : Julian Mantle’s world was always limited to his work, material possession and money. However, his life takes a complete U turn when he is suddenly traumatized with a heart attack. Realization dawns on him and he decides to alter the course of his life. It is then that he decides to give up everything and entail a spiritual journey to the Himalayas in India in search of a more meaningful life.
     * cover : http://readrush.in/img/monk_cover.jpg
     * genre : Literary Non-Fiction
     * attr : 1,4
     * url : null
     * cost : 0
     * type : Concise
     * datetime : 2017-11-05 22:46:19
     */

    private String rush_id;
    private String title;
    private String author;
    private String rating;
    private String est_time;
    private String pages;
    private Object audio;
    private Object video;
    private String description;
    private String cover;
    private String genre;
    private String attr;
    private Object url;
    private String cost;
    private String type;
    private String datetime;

    public String getRush_id() {
        return rush_id;
    }

    public void setRush_id(String rush_id) {
        this.rush_id = rush_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEst_time() {
        return est_time;
    }

    public void setEst_time(String est_time) {
        this.est_time = est_time;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Object getAudio() {
        return audio;
    }

    public void setAudio(Object audio) {
        this.audio = audio;
    }

    public Object getVideo() {
        return video;
    }

    public void setVideo(Object video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}