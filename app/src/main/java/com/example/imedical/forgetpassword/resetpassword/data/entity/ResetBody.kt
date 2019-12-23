package com.example.imedical.forgetpassword.resetpassword.data.entity

import com.google.gson.annotations.SerializedName

data class ResetBody (
    @SerializedName("mobile") val mobile: String,
    @SerializedName("sms_activation") val verificationCode: String,
    @SerializedName("password") val password: String,
    @SerializedName("password_confirmation") val passwordConfirmation: String
)
