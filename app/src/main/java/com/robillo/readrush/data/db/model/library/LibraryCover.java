package com.robillo.readrush.data.db.model.library;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by robinkamboj on 09/12/17.
 */

@Entity(tableName = "library_cover")
public class LibraryCover {

    @SuppressWarnings("WeakerAccess")
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "rush_id")
    String rushId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "est_time")
    private String estTime;

    @ColumnInfo(name = "pages")
    private String pages;

    public LibraryCover(@NonNull String rushId, String title, String author, String rating, String estTime, String pages) {
        this.rushId = rushId;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.estTime = estTime;
        this.pages = pages;
    }

    @NonNull
    public String getRushId() {
        return rushId;
    }

    public void setRushId(@NonNull String rushId) {
        this.rushId = rushId;
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

    public String getEstTime() {
        return estTime;
    }

    public void setEstTime(String estTime) {
        this.estTime = estTime;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
