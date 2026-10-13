package com.orvi.productsland.domain.usecase

import com.orvi.productsland.domain.model.Product
import com.orvi.productsland.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.observeProducts()
}
