package com.robillo.readrush.data.network.retrofit.model;

import java.util.List;

/**
 * Created by robinkamboj on 25/12/17.
 */

public class ProfileNumbersSuper {

    /**
     * reading : 1
     * read : 5
     * rushes_read : [{"rush_id":"1","title":"The Monk who sold his Ferrari","cover":"http://readrush.in/img/monk_cover.jpg"},{"rush_id":"3","title":"Outliers: The Story of Success ","cover":"http://readrush.in/img/outliers_cover.jpg"},{"rush_id":"4","title":"The Power of your Subconscious Mind","cover":"http://readrush.in/img/powerofsubmind_cover.jpg"},{"rush_id":"5","title":"The Subtle Art of Not Giving a F*ck","cover":"http://readrush.in/img/subtle_cover.jpg"}]
     */

    private int reading;
    private int read;
    private List<RushesReadBean> rushes_read;

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public List<RushesReadBean> getRushes_read() {
        return rushes_read;
    }

    public void setRushes_read(List<RushesReadBean> rushes_read) {
        this.rushes_read = rushes_read;
    }

    public static class RushesReadBean {
        /**
         * rush_id : 1
         * title : The Monk who sold his Ferrari
         * cover : http://readrush.in/img/monk_cover.jpg
         */

        private String rush_id;
        private String title;
        private String cover;

        public String getRush_id() {
            return rush_id;
        }

        public void setRush_id(String rush_id) {
            this.rush_id = rush_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
