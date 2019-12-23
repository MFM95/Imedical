package com.example.imedical.categories.data.entity

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("childsCount")
    val childrenCount : Int?,
    @SerializedName("childs")
    val children : List<FirstChildren>?
)

data class FirstChildren (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("childsCount")
    val childrenCount : Int?,
    @SerializedName("childs")
    val children : List<SecondChildren>?
)

data class SecondChildren (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("childsCount")
    val childrenCount : Int?
)
