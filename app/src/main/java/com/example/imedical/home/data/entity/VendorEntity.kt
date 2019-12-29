package com.example.imedical.home.data.entity

import com.google.gson.annotations.SerializedName

data class VendorEntity (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)