package com.example.imedical.categories.domain.model

data class CategoryModel (
    val id: Int?,
    val name: String?,
    val childrenCount : Int?,
    val children : ArrayList<FirstChildren>?,
    var isExpanded: Boolean? = false
)

data class FirstChildren (
    val id: Int?,
    val name: String?,
    val childrenCount : Int?,
    val children : ArrayList<SecondChildren>?,
    var isExpanded: Boolean? = false
)

data class SecondChildren (
    val id: Int?,
    val name: String?,
    val childrenCount : Int?
)
