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

    void setUserId(String userId);
    String getUserId();

    void setUserName(String userName);
    String getUserName();

    void setUserPassword(String userPassword);
    String getUserPassword();

    void setUserPreference(String userPreference);
    String getUserPreference();

    void setRushCount(int rushCount);
    int getRushCount();

    void setUserEmail(String email);
    String getUserEmail();

    void setLibrary(String library);
    String getLibrary();

    void setRead(String read);
    String getRead();

    void setFacebookId(String facebookId);
    String getFacebookId();

    void setGoogleId(String googleId);
    String getGoogleId();

    void setDisplayPicture(String displayPicture);
    String getDisplayPicture();

    void setPreferenceCode(String preferenceCode);
    String getPreferenceCode();

    void setDateTime(String dateTime);
    String getDateTime();

    //APP

    void setUserIsLoggedIn(@SuppressWarnings("SameParameterValue") boolean isLoggedIn);
    boolean getUserIsLoggedIn();

    void setUserIsOnBoarded(@SuppressWarnings("SameParameterValue") boolean isOnBoarded);
    boolean getUserIsOnBoarded();

    void setUserEnterMode(String mode);
    String getUserEnterMode();

    void setAppTheme(String theme);
    String getAppTheme();

    void setContentPadding(int padding);
    int getContentPadding();

    void setLineSpacing(float spacing);
    float getLineSpacing();

    void setTextSize(int size);
    int getTextSize();

    void setFontPath(String path);
    String getFontPath();
}