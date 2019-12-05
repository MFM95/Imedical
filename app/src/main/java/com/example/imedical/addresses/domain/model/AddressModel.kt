package com.example.imedical.addresses.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class AddressModel (
    val id: Int? = 0,
    var alias: String?,
    var address_1: String?,
    var address_2: String?,
    var phone: String?,
    var country: Country?,
    var province: Province?
)

data class Country(
    val id: Int?,
    val name: String?
)

data class Province(
    var id: Int?,
    var name: String?
)