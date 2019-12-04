package com.example.imedical.addresses.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AddressModel (
    val id: Int? = 0,
    val alias: String?,
    val address_1: String?,
    val address_2: String?,
    val phone: String?,
    val country: Country?,
    val province: Province?
)

data class Country(
    val id: Int?,
    val name: String?
)

data class Province(
    val id: Int?,
    val name: String?
)