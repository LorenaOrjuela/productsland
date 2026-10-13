package com.orvi.productsland.domain.usecase

import com.orvi.productsland.domain.repository.ProductRepository
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.refreshProducts()
}
