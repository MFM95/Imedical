package com.example.imedical.compare.data.entity

import android.arch.persistence.room.PrimaryKey


data class BrandEntity(
    @PrimaryKey val name: String
)