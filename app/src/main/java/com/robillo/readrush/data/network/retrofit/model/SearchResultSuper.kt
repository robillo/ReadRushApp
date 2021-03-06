package com.robillo.readrush.data.network.retrofit.model

/**
 * Created by robinkamboj on 28/11/17.
 */

/**
 * status : success
 * message : [{"rush_id":"1","title":"The monk who sold his Ferrari","author":"Robin Sharma","rating":"3.8","est_time":"11","pages":"10","audio":null,"video":null,"description":"Julian Mantle\u2019s world was always limited to his work, material possession and money. However, his life takes a complete U turn when he is suddenly traumatized with a heart attack. Realization dawns on him and he decides to alter the course of his life. It is then that he decides to give up everything and entail a spiritual journey to the Himalayas in India in search of a more meaningful life.","cover":"http://readrush.in/img/monk_cover.jpg","genre":"Literary Non-Fiction","attr":"1,4","url":null,"cost":"0","type":"Concise","datetime":"2017-11-05 22:46:19"},{"rush_id":"2","title":"The monk who sold his Ferrari 2","author":"Robin Sharma","rating":"3.8","est_time":"11","pages":"10","audio":null,"video":null,"description":"Julian Mantle\u2019s world was always limited to his work, material possession and money. However, his life takes a complete U turn when he is suddenly traumatized with a heart attack. Realization dawns on him and he decides to alter the course of his life. It is then that he decides to give up everything and entail a spiritual journey to the Himalayas in India in search of a more meaningful life.","cover":"http://readrush.in/img/monk_cover.jpg","genre":"Literary Non-Fiction","attr":"2,3,11","url":null,"cost":"0","type":"Concise","datetime":"2017-11-05 22:46:19"}]
 */

class SearchResultSuper(var status: String?, var message: List<SearchResultItem>?)
