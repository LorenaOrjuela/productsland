package com.orvi.productsland.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orvi.productsland.data.local.dao.ProductDao
import com.orvi.productsland.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BusinessUniverseDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
