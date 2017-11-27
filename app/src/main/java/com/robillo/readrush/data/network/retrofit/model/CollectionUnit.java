package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 27/11/17.
 */

public class CollectionUnit {

    public CollectionUnit(String collection_id, String cover_image, String id) {
        this.collection_id = collection_id;
        this.cover_image = cover_image;
        this.id = id;
    }

    /**
     * collection_id : 1
     * cover_image : http://readrush.in/img/collection1.jpg
     * id : 9
     */

    private String collection_id;
    private String cover_image;
    private String id;

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}