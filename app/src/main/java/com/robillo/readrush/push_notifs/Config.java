package com.robillo.readrush.push_notifs;

/**
 * Created by robinkamboj on 26/12/17.
 */

@SuppressWarnings("WeakerAccess")
public class Config {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // global topic to receive app wide push notifications
    public static final String TOPIC_LOCAL = "my_personalised_rushes";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "read_rush_firebase";

}
