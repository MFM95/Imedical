package com.example.imedical.core.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.imedical.compare.data.entity.ProductEntity
import com.example.imedical.compare.data.local.CompareListDao

@Database(entities = [ProductEntity::class], version = 1)
abstract class ImedicalDatabase: RoomDatabase() {

    abstract fun compareListDao(): CompareListDao

}