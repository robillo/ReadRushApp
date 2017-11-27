package com.robillo.readrush.data.network.retrofit.model;

import java.util.List;

/**
 * Created by robinkamboj on 27/11/17.
 */

public class CollectionsSuper {

    public CollectionsSuper(String status, List<CollectionUnit> message) {
        this.status = status;
        this.message = message;
    }

    /**
     * status : success
     * message : [{"collection_id":"1","cover_image":"http://readrush.in/img/collection1.jpg","id":"9"},{"collection_id":"2","cover_image":"http://readrush.in/img/collection2.jpg","id":"10"},{"collection_id":"3","cover_image":"http://readrush.in/img/collection3.jpg","id":"11"},{"collection_id":"4","cover_image":"http://readrush.in/img/collection4.jpg","id":"12"}]
     */

    private String status;
    private List<CollectionUnit> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CollectionUnit> getMessage() {
        return message;
    }

    public void setMessage(List<CollectionUnit> message) {
        this.message = message;
    }
}
