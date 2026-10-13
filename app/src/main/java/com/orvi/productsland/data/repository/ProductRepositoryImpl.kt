package com.orvi.productsland.data.repository

import com.orvi.productsland.data.local.dao.ProductDao
import com.orvi.productsland.data.mapper.toDomain
import com.orvi.productsland.data.mapper.toEntity
import com.orvi.productsland.data.remote.ProductApiService
import com.orvi.productsland.domain.model.Product
import com.orvi.productsland.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao,
    private val api: ProductApiService
) : ProductRepository {

    override fun observeProducts(): Flow<List<Product>> {
        return dao.getAllProducts().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun refreshProducts() {
        val response = api.getProducts()
        dao.upsertAll(response.products.map { it.toEntity() })
    }
}
