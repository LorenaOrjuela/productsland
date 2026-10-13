package com.orvi.productsland.domain.repository

import com.orvi.productsland.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun observeProducts(): Flow<List<Product>>
    suspend fun refreshProducts()
}
