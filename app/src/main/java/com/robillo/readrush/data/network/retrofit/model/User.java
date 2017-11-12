package com.robillo.readrush.data.network.retrofit.model;

/**
 * Created by robinkamboj on 12/11/17.
 */

public class User {

    /**
     * user_id : 6
     * name : Oversmartpeople1!
     * password : 7994b737aae797b37ca517adcf9d1882
     * preference : Psychology,Biography and Autobiography
     * rush_count : 0
     * email_id : robinkamboj975@gmail.com
     * library : null
     * read : null
     * facebook_id : null
     * google_id : null
     * display_picture : http://readrush.in/app_data/images/dp/default_pic.jpg
     * preference_code : 11111
     * datetime : 2017-11-12 00:05:42
     */

    private String user_id;
    private String name;
    private String password;
    private String preference;
    private String rush_count;
    private String email_id;
    private Object library;
    private Object read;
    private Object facebook_id;
    private Object google_id;
    private String display_picture;
    private String preference_code;
    private String datetime;

    public User(String user_id, String name, String password, String preference, String rush_count, String email_id, Object library, Object read, Object facebook_id, Object google_id, String display_picture, String preference_code, String datetime) {
        this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.preference = preference;
        this.rush_count = rush_count;
        this.email_id = email_id;
        this.library = library;
        this.read = read;
        this.facebook_id = facebook_id;
        this.google_id = google_id;
        this.display_picture = display_picture;
        this.preference_code = preference_code;
        this.datetime = datetime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getRush_count() {
        return rush_count;
    }

    public void setRush_count(String rush_count) {
        this.rush_count = rush_count;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public Object getLibrary() {
        return library;
    }

    public void setLibrary(Object library) {
        this.library = library;
    }

    public Object getRead() {
        return read;
    }

    public void setRead(Object read) {
        this.read = read;
    }

    public Object getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(Object facebook_id) {
        this.facebook_id = facebook_id;
    }

    public Object getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(Object google_id) {
        this.google_id = google_id;
    }

    public String getDisplay_picture() {
        return display_picture;
    }

    public void setDisplay_picture(String display_picture) {
        this.display_picture = display_picture;
    }

    public String getPreference_code() {
        return preference_code;
    }

    public void setPreference_code(String preference_code) {
        this.preference_code = preference_code;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
