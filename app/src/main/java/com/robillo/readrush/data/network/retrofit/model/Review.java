package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 28/11/17.
 */

public class Review {

    public Review(String review_id, String rating, String review, String datetime, String name, String display_picture) {
        this.review_id = review_id;
        this.rating = rating;
        this.review = review;
        this.datetime = datetime;
        this.name = name;
        this.display_picture = display_picture;
    }

    /**
     * review_id : 1
     * rating : 5
     * review : Amazing read. Nicely summarized.
     * datetime : 2017-11-05 23:23:58
     * name : Robin
     * display_picture : http://readrush.in/app_data/images/dp/default_pic.jpg
     */

    private String review_id;
    private String rating;
    private String review;
    private String datetime;
    private String name;
    private String display_picture;

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_picture() {
        return display_picture;
    }

    public void setDisplay_picture(String display_picture) {
        this.display_picture = display_picture;
    }
}
