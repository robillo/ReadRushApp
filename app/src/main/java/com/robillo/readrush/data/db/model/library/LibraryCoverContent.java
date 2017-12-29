package com.robillo.readrush.data.db.model.library;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Created by robinkamboj on 29/12/17.
 */

@Entity( foreignKeys = @ForeignKey( entity = LibraryCover.class, parentColumns = "rush_id", childColumns = "rush_id"))
public class LibraryCoverContent {

    public LibraryCoverContent(String content_id, String rush_id, String content, String attr, String datetime) {
        this.content_id = content_id;
        this.rush_id = rush_id;
        this.content = content;
        this.attr = attr;
        this.datetime = datetime;
    }

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

}
