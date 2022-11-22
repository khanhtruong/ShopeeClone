package com.khanhtruong.shopeeclone.service.network

import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("/product")
    suspend fun getProductDataSet(after: Int, limit: Int = 15): Response<List<ProductDomain>>
}