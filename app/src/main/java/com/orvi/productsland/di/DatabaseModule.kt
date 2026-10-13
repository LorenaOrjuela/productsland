package com.orvi.productsland.di

import android.content.Context
import androidx.room.Room
import com.orvi.productsland.data.local.dao.ProductDao
import com.orvi.productsland.data.local.database.BusinessUniverseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun businessUniverseDatabase(
        @ApplicationContext context: Context
    ): BusinessUniverseDatabase {
        return Room.databaseBuilder(
            context,
            BusinessUniverseDatabase::class.java,
            "business_universe.db"
        ).build()
    }

    @Provides
    fun productDao(database: BusinessUniverseDatabase): ProductDao {
        return database.productDao()
    }
}
