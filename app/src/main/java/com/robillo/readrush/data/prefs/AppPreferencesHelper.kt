package com.robillo.readrush.data.prefs

import android.content.Context
import android.content.SharedPreferences

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


    public override fun setUserEmail(email: String) {
        mPrefs.edit().putString(PREF_KEY_EMAIL, email).apply();
    }

    public override fun getUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_EMAIL, null);
    }

    public override fun setUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_USERNAME, userName).apply();
    }

    public override fun getUserName(): String {
        return mPrefs.getString(PREF_KEY_USERNAME, null);
    }

    public override fun setUserPassword(userPassword: String?) {
        mPrefs.edit().putString(PREF_KEY_PASSWORD, userPassword).apply();
    }

    public override fun getUserPassword(): String {
        return mPrefs.getString(PREF_KEY_PASSWORD, null);
    }

    public override fun setUserPreference1(userPreference1: String?) {
        mPrefs.edit().putString(PREF_KEY_PREFERENCE_1, userPreference1).apply();
    }

    public override fun getUserPreference1(): String {
        return mPrefs.getString(PREF_KEY_PREFERENCE_1, null);
    }

    public override fun setUserPreference2(userPreference2: String?) {
        mPrefs.edit().putString(PREF_KEY_PREFERENCE_2, userPreference2).apply();
    }

    public override fun getUserPreference2(): String {
        return mPrefs.getString(PREF_KEY_PREFERENCE_2, null);
    }

    public override fun setUserIsLoggedIn(isLoggedIn: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public override fun getUserIsLoggedIn(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_LOGGED_IN, false);
    }

    public override fun setUserIsOnBoarded(isOnBoarded: Boolean) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_ON_BOARDED, isOnBoarded).apply();
    }

    public override fun getUserIsOnBoarded(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_IS_ON_BOARDED, false);
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
        private val PREF_KEY_EMAIL = "PREF_KEY_EMAIL"
        private val PREF_KEY_USERNAME = "PREF_KEY_USERNAME"
        private val PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD"
        private val PREF_KEY_PREFERENCE_1 = "PREF_KEY_PREFERENCE_1"
        private val PREF_KEY_PREFERENCE_2 = "PREF_KEY_PREFERENCE_2"
    }
}
