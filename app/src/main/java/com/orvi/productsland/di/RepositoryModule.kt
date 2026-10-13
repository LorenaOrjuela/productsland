package com.orvi.productsland.di

import com.orvi.productsland.data.repository.ProductRepositoryImpl
import com.orvi.productsland.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun productRepository(impl: ProductRepositoryImpl): ProductRepository
}
