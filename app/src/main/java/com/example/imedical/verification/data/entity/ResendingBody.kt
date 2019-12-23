package com.example.imedical.verification.data.entity

import com.google.gson.annotations.SerializedName


data class ResendingBody  (
    @SerializedName("mobile")
    val mobile: String
)