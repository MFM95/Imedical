package com.example.imedical.compare.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.imedical.compare.data.entity.ProductEntity

@Dao
interface CompareListDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Insert
    fun insert(productEntity: ProductEntity)

}