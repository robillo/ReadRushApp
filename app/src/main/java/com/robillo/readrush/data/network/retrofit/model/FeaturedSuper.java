package com.robillo.readrush.data.network.retrofit.model;

import java.util.List;

/**
 * Created by robinkamboj on 27/11/17.
 */

public class FeaturedSuper {

    public FeaturedSuper(String status, List<Featured> message) {
        this.status = status;
        this.message = message;
    }

    /**
     * status : success
     * message : [{"id":"3","rush_id":"1","cover_image":"http://readrush.in/img/christmas_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:14:34"},{"id":"4","rush_id":"2","cover_image":"http://readrush.in/img/satanic_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:14:34"},{"id":"5","rush_id":"1","cover_image":"http://readrush.in/img/bill_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:15:18"},{"id":"6","rush_id":"2","cover_image":"http://readrush.in/img/alice_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:15:18"},{"id":"7","rush_id":"1","cover_image":"http://readrush.in/img/seven_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:15:58"},{"id":"8","rush_id":"2","cover_image":"http://readrush.in/img/adultery_cover.jpg","type":"featured","status":"Active","datetime":"2017-11-26 02:15:58"}]
     */

    private String status;
    private List<Featured> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Featured> getMessage() {
        return message;
    }

    public void setMessage(List<Featured> message) {
        this.message = message;
    }
}
