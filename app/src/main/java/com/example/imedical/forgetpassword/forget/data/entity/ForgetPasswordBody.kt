package com.example.imedical.forgetpassword.forget.data.entity

import com.google.gson.annotations.SerializedName

data class ForgetPasswordBody (
    @SerializedName("mobile")
    val mobile: String
)