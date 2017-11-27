package com.robillo.readrush.data.network.retrofit.model;

import java.util.List;

/**
 * Created by robinkamboj on 27/11/17.
 */

public class CoverSuper {

    public CoverSuper(String status, List<Cover> message) {
        this.status = status;
        this.message = message;
    }

    /**
     * status : success
     * message : [{"id":"1","rush_id":"1","cover_image":"http://readrush.in/img/top_cover1.jpg","type":"cover","status":"Active","datetime":"2017-11-26 02:04:19"},{"id":"2","rush_id":"2","cover_image":"http://readrush.in/img/top_cover2.jpg","type":"cover","status":"Active","datetime":"2017-11-26 02:04:19"}]
     */

    private String status;
    private List<Cover> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Cover> getMessage() {
        return message;
    }

    public void setMessage(List<Cover> message) {
        this.message = message;
    }
}
