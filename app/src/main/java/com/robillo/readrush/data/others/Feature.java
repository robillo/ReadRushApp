package com.robillo.readrush.data.others;

/**
 * Created by robinkamboj on 20/10/17.
 */

public class Feature {

    private int drawableId;
    private String bookId;

    public Feature(int drawableId, String bookId) {
        this.drawableId = drawableId;
        this.bookId = bookId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
