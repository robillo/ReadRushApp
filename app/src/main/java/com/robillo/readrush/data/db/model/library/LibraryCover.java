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

    @ColumnInfo(name = "cover")
    private String cover;

    @ColumnInfo(name = "rush_content")
    private String rush_content;

    @ColumnInfo(name = "rush_audio")
    private boolean rush_audio;

    public LibraryCover(@NonNull String rushId, String title, String author, String rating, String estTime, String pages, String cover, String rush_content, boolean rush_audio) {
        this.rushId = rushId;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.estTime = estTime;
        this.pages = pages;
        this.cover = cover;
        this.rush_content = rush_content;
        this.rush_audio = rush_audio;
    }

    @SuppressWarnings("WeakerAccess")
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

    @SuppressWarnings("WeakerAccess")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @SuppressWarnings("WeakerAccess")
    public String getEstTime() {
        return estTime;
    }

    public void setEstTime(String estTime) {
        this.estTime = estTime;
    }

    @SuppressWarnings("WeakerAccess")
    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRush_content() {
        return rush_content;
    }

    public void setRush_content(String rush_content) {
        this.rush_content = rush_content;
    }

    public boolean isRush_audio() {
        return rush_audio;
    }

    public void setRush_audio(boolean rush_audio) {
        this.rush_audio = rush_audio;
    }
}
