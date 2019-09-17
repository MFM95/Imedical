package com.example.imedical.addresses.domain.model

data class AddressModel (
    val alias: String?,
    val address_1: String?,
    val address_2: String?,
    val phone: String?,
    val country: Country?,
    val province: String?
)

data class Country(
    val id: Int?,
    val name: String?
)