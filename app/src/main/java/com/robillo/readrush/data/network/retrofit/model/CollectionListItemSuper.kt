package com.robillo.readrush.data.network.retrofit.model

/**
 * Created by robinkamboj on 03/12/17.
 */

class CollectionListItemSuper {

    /**
     * status : success
     * message : [{"id":"2","rush_id":"2","cover_image":"http://readrush.in/img/monk_cover.jpg","collection_id":"2","status":"Active","datetime":"2017-11-26 02:52:26"},{"id":"5","rush_id":"1","cover_image":"http://readrush.in/img/monk_cover.jpg","collection_id":"2","status":"Active","datetime":"2017-11-26 02:52:26"},{"id":"9","rush_id":"1","cover_image":"http://readrush.in/img/monk_cover.jpg","collection_id":"2","status":"Active","datetime":"2017-11-26 02:52:26"}]
     */

    var status: String? = null
    var message: List<CollectionListItem>? = null

}
