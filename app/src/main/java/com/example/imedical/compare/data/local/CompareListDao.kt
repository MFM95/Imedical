package com.example.imedical.compare.data.local

import android.arch.persistence.room.*
import com.example.imedical.compare.data.entity.ProductEntity

@Dao
interface CompareListDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productEntity: ProductEntity)

    @Delete
    fun delete(productEntity: ProductEntity)
}