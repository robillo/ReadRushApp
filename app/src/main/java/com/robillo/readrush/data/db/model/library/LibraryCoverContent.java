package com.robillo.readrush.data.db.model.library;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by robinkamboj on 29/12/17.
 */

@Entity(tableName = "LibraryCoverContent", foreignKeys = @ForeignKey( entity = LibraryCover.class, parentColumns = "rush_id", childColumns = "rush_id", onDelete = ForeignKey.CASCADE))
public class LibraryCoverContent {

    public LibraryCoverContent(String content_id, String rush_id, String content, String attr, String datetime, String page) {
        this.content_id = content_id;
        this.rush_id = rush_id;
        this.content = content;
        this.attr = attr;
        this.datetime = datetime;
        this.page = page;
        this.primary = content + rush_id;
    }

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "primary")
    private String primary;

    @ColumnInfo(name = "content_id")
    private String content_id;

    @ColumnInfo(name = "rush_id")
    private String rush_id;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "attr")
    private String attr;

    @ColumnInfo(name = "datetime")
    private String datetime;

    @ColumnInfo(name = "page")
    private String page;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getRush_id() {
        return rush_id;
    }

    public void setRush_id(String rush_id) {
        this.rush_id = rush_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
