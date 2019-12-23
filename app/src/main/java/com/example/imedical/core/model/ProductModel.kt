package com.example.imedical.core.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Ahmed Hassan on 8/18/2019.
 */

data class ProductModel constructor(
    val id: Int,
    val name: String,
    val description: String?,
    val imageUrl: String,
    val price: Double,
    val salePrice: Double?,
    var inWishList: Boolean,
    var inCompareList: Boolean,
    val brand: String?,
    val quantity: Int,
    var index: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeDouble(price)
        parcel.writeValue(salePrice)
        parcel.writeByte(if (inWishList) 1 else 0)
        parcel.writeByte(if (inCompareList) 1 else 0)
        parcel.writeString(brand)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}