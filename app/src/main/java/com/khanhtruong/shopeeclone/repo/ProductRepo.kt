package com.khanhtruong.shopeeclone.repo

import com.khanhtruong.shopeeclone.data.Resource
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import kotlinx.coroutines.flow.Flow

interface ProductRepo {
    fun getProductDataSet(page: Int, limit: Int): Flow<Resource<List<ProductDomain>>>
    fun getProductPagingSource(): ProductPagingSource
}