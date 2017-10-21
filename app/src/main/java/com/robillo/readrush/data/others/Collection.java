package com.robillo.readrush.data.others;

/**
 * Created by robinkamboj on 21/10/17.
 */

public class Collection {

    private int drawableId;
    private String header;
    private String collectionId;

    public Collection(int drawableId, String header, String collectionId) {
        this.drawableId = drawableId;
        this.header = header;
        this.collectionId = collectionId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
