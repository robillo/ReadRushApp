package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 03/12/17.
 */

public class CollectionListItem {
    /**
     * id : 2
     * rush_id : 2
     * cover_image : http://readrush.in/img/monk_cover.jpg
     * collection_id : 2
     * status : Active
     * datetime : 2017-11-26 02:52:26
     */

    private String id;
    private String rush_id;
    private String cover_image;
    private String collection_id;
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

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
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