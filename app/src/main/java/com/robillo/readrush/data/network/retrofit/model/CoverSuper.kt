package com.robillo.readrush.data.network.retrofit.model

/**
 * Created by robinkamboj on 27/11/17.
 */


/**
 * status : success
 * message : [{"id":"1","rush_id":"1","cover_image":"http://readrush.in/img/top_cover1.jpg","type":"cover","status":"Active","datetime":"2017-11-26 02:04:19"},{"id":"2","rush_id":"2","cover_image":"http://readrush.in/img/top_cover2.jpg","type":"cover","status":"Active","datetime":"2017-11-26 02:04:19"}]
 */

class CoverSuper(var status: String?, var message: List<Cover>?)
