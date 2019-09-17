package com.example.imedical.addresses.data.entity

import com.google.gson.annotations.SerializedName

data class AddressResponse (

    @SerializedName("alias")
    val alias: String?,

    @SerializedName("address_1")
    val address_1: String?,

    @SerializedName("address_2")
    val address_2: String?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("country")
    val country: Country?,

    @SerializedName("province")
    val province: String?
    )

data class Country(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)