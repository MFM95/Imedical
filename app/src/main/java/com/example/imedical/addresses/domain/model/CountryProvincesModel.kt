package com.example.imedical.addresses.domain.model

data class CountryProvincesModel (
    val id: String,
    val name: String,
    val provinces: List<Province>
)
