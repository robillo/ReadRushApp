package com.robillo.readrush.data.prefs;

import com.robillo.readrush.data.DataManager;

/**
 * Created by robinkamboj on 08/10/17.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    //RR

    void setUserEmail(String email);

    String getUserEmail();

    void setUserName(String userName);

    String getUserName();

    void setUserPassword(String userPassword);

    String getUserPassword();

    void setUserPreference1(String userPreference1);

    String getUserPreference1();

    void setUserPreference2(String userPreference2);

    String getUserPreference2();

    void setUserIsLoggedIn(boolean isLoggedIn);

    boolean getUserIsLoggedIn();

    void setUserIsOnBoarded(@SuppressWarnings("SameParameterValue") boolean isOnBoarded);

    boolean getUserIsOnBoarded();

    void setUserEnterMode(String mode);

    String getUserEnterMode();

}
