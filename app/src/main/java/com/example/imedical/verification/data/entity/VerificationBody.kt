package com.example.imedical.verification.data.entity

import com.google.gson.annotations.SerializedName


data class VerificationBody  (
    @SerializedName("sms_activation")
    val code: String
)