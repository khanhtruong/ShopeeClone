package com.khanhtruong.shopeeclone.repo

import com.khanhtruong.shopeeclone.data.Resource
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import com.khanhtruong.shopeeclone.di.qualifier.ProductAPIProvider
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import com.khanhtruong.shopeeclone.service.safeCallApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    @ProductAPIProvider private val apiProduct: ProductAPI
) : ProductRepo {
    override fun getProductDataSet(page: Int, limit: Int): Flow<Resource<List<ProductDomain>>> {
        return flow {
            val data = safeCallApi {
                apiProduct.getProductDataSet(page, limit)
            }
            emit(data)
        }
    }

    override fun getProductPagingSource() = ProductPagingSource(apiProduct)
}