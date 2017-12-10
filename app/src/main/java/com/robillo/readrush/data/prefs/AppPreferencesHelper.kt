package com.robillo.readrush.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.robillo.readrush.ReadRushApp

import com.robillo.readrush.data.DataManager
import com.robillo.readrush.di.ApplicationContext
import com.robillo.readrush.di.PreferenceInfo
import com.robillo.readrush.utils.AppConstants

import javax.inject.Inject

/**
 * Created by robinkamboj on 08/10/17.
 */

class AppPreferencesHelper @Inject
constructor(@ApplicationContext context: Context, @PreferenceInfo prefFileName: String) : PreferencesHelper {

    private val mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return if (userId == AppConstants.NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserName(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(userName: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()
    }

    override fun getCurrentUserLoggedInMode(): Int {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)
    }

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type).apply()
    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    //RR SHAREDPREFS FUNCTIONS

    override fun setUserIsLoggedIn(isLoggedIn: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    override fun getUserIsLoggedIn(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGGED_IN, false);
    }

    override fun setUserIsOnBoarded(isOnBoarded: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_ON_BOARDED, isOnBoarded).apply();
    }

    override fun getUserIsOnBoarded(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_ON_BOARDED, false);
    }

    //USER

    override fun setUserId(userId: String?) {
        mPrefs.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }

    override fun getUserId(): String {
        return mPrefs.getString(PREF_KEY_USER_ID, null);
    }

    override fun setUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_USERNAME, userName).apply();
    }

    override fun getUserName(): String {
        return mPrefs.getString(PREF_KEY_USERNAME, null);
    }

    override fun setUserPassword(userPassword: String?) {
        mPrefs.edit().putString(PREF_KEY_PASSWORD, userPassword).apply();
    }

    override fun getUserPassword(): String {
        return mPrefs.getString(PREF_KEY_PASSWORD, null);
    }

    override fun setUserPreference(userPreference: String?) {
        mPrefs.edit().putString(PREF_KEY_PREFERENCE, userPreference).apply();
    }

    override fun getUserPreference(): String {
        return mPrefs.getString(PREF_KEY_PREFERENCE, null);
    }

    override fun setRushCount(rushCount: Int) {
        mPrefs.edit().putInt(PREF_KEY_RUSH_COUNT, rushCount).apply();
    }

    override fun getRushCount(): Int {
        return mPrefs.getInt(PREF_KEY_RUSH_COUNT, 0);
    }

    override fun setUserEmail(email: String) {
        mPrefs.edit().putString(PREF_KEY_EMAIL, email).apply();
    }

    override fun getUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_EMAIL, null);
    }

    override fun setLibrary(library: String?) {
        mPrefs.edit().putString(PREF_KEY_LIBRARY, library).apply();
    }

    override fun getLibrary(): String {
        return mPrefs.getString(PREF_KEY_LIBRARY, null);
    }

    override fun setRead(read: String?) {
        mPrefs.edit().putString(PREF_KEY_READ, read).apply();
    }

    override fun getRead(): String {
        return mPrefs.getString(PREF_KEY_READ, null);
    }

    override fun setFacebookId(facebookId: String?) {
        mPrefs.edit().putString(PREF_KEY_FACEBOOK_ID, facebookId).apply();
    }

    override fun getFacebookId(): String {
        return mPrefs.getString(PREF_KEY_FACEBOOK_ID, null);
    }

    override fun setGoogleId(googleId: String?) {
        mPrefs.edit().putString(PREF_KEY_GOOGLE_ID, googleId).apply();
    }

    override fun getGoogleId(): String {
        return mPrefs.getString(PREF_KEY_GOOGLE_ID, null);
    }

    override fun setDisplayPicture(displayPicture: String?) {
        mPrefs.edit().putString(PREF_KEY_DISPLAY_PICTURE, displayPicture).apply();
    }

    override fun getDisplayPicture(): String {
        return mPrefs.getString(PREF_KEY_DISPLAY_PICTURE, null);
    }

    override fun setPreferenceCode(preferenceCode: String?) {
        mPrefs.edit().putString(PREF_KEY_PREFERENCE_CODE, preferenceCode).apply();
    }

    override fun getPreferenceCode(): String {
        return mPrefs.getString(PREF_KEY_PREFERENCE_CODE, null);
    }

    override fun setDateTime(dateTime: String?) {
        mPrefs.edit().putString(PREF_KEY_DATE_TIME, dateTime).apply();
    }

    override fun getDateTime(): String {
        return mPrefs.getString(PREF_KEY_DATE_TIME, null);
    }

    //LOGIN OR REGISTER MODE
    override fun setUserEnterMode(mode: String?) {
        mPrefs.edit().putString(PREF_KEY_ENTER_MODE, mode).apply();
    }

    override fun getUserEnterMode(): String {
        return  mPrefs.getString(PREF_KEY_ENTER_MODE, ReadRushApp.REGISTER_MODE);
    }

    //APP THEME DAY OR NIGHT
    override fun setAppTheme(theme: String?) {
        mPrefs.edit().putString(PREF_KEY_APP_THEME, theme).apply();
    }

    override fun getAppTheme(): String {
        return  mPrefs.getString(PREF_KEY_APP_THEME, "DAY");
    }

    override fun setContentPadding(padding: Int) {
        mPrefs.edit().putInt(PREF_KEY_CONTENT_PADDING, padding).apply();
    }

    override fun getContentPadding(): Int {
        return mPrefs.getInt(PREF_KEY_CONTENT_PADDING, 60);
    }

    override fun setLineSpacing(spacing: Float) {
        mPrefs.edit().putFloat(PREF_KEY_LINE_SPACING, spacing).apply();
    }

    override fun getLineSpacing(): Float {
        return mPrefs.getFloat(PREF_KEY_LINE_SPACING, 1.5F);
    }

    override fun setTextSize(size: Int) {
        mPrefs.edit().putInt(PREF_KEY_TEXT_SIZE, size).apply();
    }

    override fun getTextSize(): Int {
        return mPrefs.getInt(PREF_KEY_TEXT_SIZE, 20);
    }

    companion object {

        private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

        //RR Keys

        //booleans
        private val PREF_KEY_IS_ON_BOARDED = "PREF_KEY_IS_ONBOARDED"
        private val PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN"

        //strings
        //USER
        private val PREF_KEY_USER_ID = "PREF_KEY_USER_ID"
        private val PREF_KEY_USERNAME = "PREF_KEY_USERNAME"
        private val PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD"
        private val PREF_KEY_PREFERENCE = "PREF_KEY_PREFERENCE"
        private val PREF_KEY_RUSH_COUNT = "PREF_KEY_RUSH_COUNT"
        private val PREF_KEY_EMAIL = "PREF_KEY_EMAIL"
        private val PREF_KEY_LIBRARY = "PREF_KEY_LIBRARY"
        private val PREF_KEY_READ = "PREF_KEY_READ"
        private val PREF_KEY_FACEBOOK_ID = "PREF_KEY_FACEBOOK_ID"
        private val PREF_KEY_GOOGLE_ID = "PREF_KEY_GOOGLE_ID"
        private val PREF_KEY_DISPLAY_PICTURE = "PREF_KEY_DISPLAY_PICTURE"
        private val PREF_KEY_PREFERENCE_CODE = "PREF_KEY_PREFERENCE_CODE"
        private val PREF_KEY_DATE_TIME = "PREF_KEY_DATE_TIME"

        //APP
        private val PREF_KEY_ENTER_MODE = "PREF_KEY_ENTER_MODE"
        private val PREF_KEY_APP_THEME = "PREF_KEY_APP_THEME"
        private val PREF_KEY_CONTENT_PADDING = "PREF_KEY_CONTENT_PADDING"
        private val PREF_KEY_LINE_SPACING = "PREF_KEY_LINE_SPACING"
        private val PREF_KEY_TEXT_SIZE = "PREF_KEY_TEXT_SIZE"
    }
}
