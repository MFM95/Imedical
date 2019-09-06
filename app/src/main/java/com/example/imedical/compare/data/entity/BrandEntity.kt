package com.example.imedical.compare.data.entity

import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class BrandEntity(
    @PrimaryKey val name: String
)