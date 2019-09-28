package com.example.imedical.addresses.data.entity

import com.google.gson.annotations.SerializedName

data class CountryProvincesResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("provinces")
    val provinces: List<Province>
)
