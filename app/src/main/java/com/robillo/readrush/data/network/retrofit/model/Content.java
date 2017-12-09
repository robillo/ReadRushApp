package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 15/11/17.
 */

public class Content {

    /**
     * content_id : 1
     * rush_id : 1
     * content : <h1>Overview</h1>The book is about key life lessons imparted by a world-class lawyer-turned sage, Julian, to his friend, John.<br>The essence of the book can be divided into 7 learnings:<br>
     <ol><li>Mastering the mind</li><li>Following your purpose</li><li>Practicing Kaizen</li><li>Living with discipline</li><li>Respecting your time</li><li>Selflessly serving others</li><li>Embracing the present</li></ol>
     * page_no : 1
     * attr : Cover
     * datetime : 2017-11-05 23:22:56
     */

    private String content_id;
    private String rush_id;
    private String content;
    private String page_no;
    private String attr;
    private String datetime;

    public Content(String content_id, String rush_id, String content, String page_no, String attr, String datetime) {
        this.content_id = content_id;
        this.rush_id = rush_id;
        this.content = content;
        this.page_no = page_no;
        this.attr = attr;
        this.datetime = datetime;
    }

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

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
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
}
