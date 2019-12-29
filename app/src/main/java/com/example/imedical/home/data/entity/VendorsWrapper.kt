package com.example.imedical.home.data.entity

import com.google.gson.annotations.SerializedName

data class VendorsWrapper (
    @SerializedName("vendors") val vendors: List<VendorEntity>
)