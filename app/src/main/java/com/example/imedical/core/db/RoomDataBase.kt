package com.example.imedical.core.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.imedical.compare.data.entity.ProductEntity
import com.example.imedical.compare.data.local.CompareListDao

@Database(entities = [ProductEntity::class], version = 2)
abstract class ImedicalDatabase: RoomDatabase() {

    abstract fun compareListDao(): CompareListDao

}