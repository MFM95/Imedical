package com.example.imedical.categories.data.entity

import com.google.gson.annotations.SerializedName

data class GetCategoriesResponse (
    @SerializedName("categories")
    val categories: List<Category>
)