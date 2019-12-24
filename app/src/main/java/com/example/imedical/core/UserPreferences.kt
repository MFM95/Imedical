package com.example.imedical.core

import android.content.SharedPreferences
import android.util.Log
import com.example.imedical.login.domain.model.UserModel
import com.google.gson.Gson
import javax.inject.Inject

class UserPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, "")
    }

    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID, userId)
        editor.apply()
        Log.i(USER_ID, userId)
    }

    fun getUserId(): String? {
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

    fun increaseCartSize(){
        var size = sharedPreferences.getInt(CART_SIZE, 0)
        size++
        sharedPreferences.edit().putInt(CART_SIZE, size).apply()
    }

    fun decreaseCartSize(){
        var size = sharedPreferences.getInt(CART_SIZE, 0)
        if(size > 0)
            size--
        sharedPreferences.edit().putInt(CART_SIZE, size).apply()
    }

    fun resetCartSize(){
        sharedPreferences.edit().putInt(CART_SIZE, 0).apply()
    }

    fun setCartSize(size: Int){
        sharedPreferences.edit().putInt(CART_SIZE, size).apply()
    }
    fun getCartSize() = sharedPreferences.getInt(CART_SIZE, 0)

    private fun clearSharedPrefs() {
        val prefs = sharedPreferences.all
        for (prefToReset in prefs.entries) {
            sharedPreferences.edit().remove(prefToReset.key).apply()
        }
    }

    fun updateCartSize(size: Int) {
        sharedPreferences.edit().putInt(CART_SIZE, size).apply()
    }

    fun saveUserObject(userModel: UserModel?){
        if(userModel != null)
            sharedPreferences.edit().putString(USER_OBJECT, Gson().toJson(userModel)).apply()
    }

    fun getUserObject(): UserModel?{
        val userString = sharedPreferences.getString(USER_OBJECT, "")
        return if(userString != null && userString.isNotEmpty())
            Gson().fromJson(sharedPreferences.getString(USER_OBJECT, ""), UserModel::class.java)
        else return null
    }
    companion object {
        const val ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
        const val USER_ID = "PREF_USER_ID"
        const val CART_SIZE = "CART_SIZE"
        const val USER_OBJECT = "USER_OBJECT"
    }
}