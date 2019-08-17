package com.example.imedical.registration.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
data class RegistrationForm(
    val name:String,
    val email: String,
    val mobile: String,
    val password: String,
    @SerializedName("password_confirmation") val passwordConf: String)