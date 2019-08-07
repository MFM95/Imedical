package com.example.imedical.core

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class UserPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "")
    }

    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID, userId)
        editor.apply()
        Log.i(USER_ID, userId)
    }

    fun getUserId(): String {
        return sharedPreferences.getString(USER_ID, "")
    }



    fun isUserLogged(): Boolean {
        if (!sharedPreferences.getString(ACCESS_TOKEN, "").equals(""))
            return true
        return false
    }

    fun clearUser() {
        clearSharedPrefs()
    }

    private fun clearSharedPrefs() {
        val prefs = sharedPreferences.all
        for (prefToReset in prefs.entries) {
            sharedPreferences.edit().remove(prefToReset.key).apply()
        }
    }

    companion object {
        const val ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
        const val USER_ID = "PREF_USER_ID"
    }
}