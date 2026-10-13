package com.orvi.productsland.data.mapper

import com.orvi.productsland.data.local.entity.ProductEntity
import com.orvi.productsland.data.remote.dto.ProductDto
import com.orvi.productsland.domain.model.Product

fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail
    )
}

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail
    )
}
