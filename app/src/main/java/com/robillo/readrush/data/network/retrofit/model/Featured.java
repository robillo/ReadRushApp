package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 27/11/17.
 */

public class Featured {

    public Featured(String id, String rush_id, String cover_image, String type, String status, String datetime) {
        this.id = id;
        this.rush_id = rush_id;
        this.cover_image = cover_image;
        this.type = type;
        this.status = status;
        this.datetime = datetime;
    }

    /**
     * id : 3
     * rush_id : 1
     * cover_image : http://readrush.in/img/christmas_cover.jpg
     * type : featured
     * status : Active
     * datetime : 2017-11-26 02:14:34
     */

    private String id;
    private String rush_id;
    private String cover_image;
    private String type;
    private String status;
    private String datetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRush_id() {
        return rush_id;
    }

    public void setRush_id(String rush_id) {
        this.rush_id = rush_id;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}