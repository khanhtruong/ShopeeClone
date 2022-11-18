package com.khanhtruong.shopeeclone.service.network

import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("/product")
    suspend fun getProductDataSet(): Response<List<ProductDomain>>
}

class MockProductAPI : ProductAPI {
    override suspend fun getProductDataSet(): Response<List<ProductDomain>> {
        return Response.success(
            listOf(
                ProductDomain(1, R.drawable.img_product_1, 10000.0, 20.0, "Ring"),
                ProductDomain(2, R.drawable.img_product_2, 20000.0, 2.0, "Nail"),
                ProductDomain(3, R.drawable.img_product_3, 30000.0, 10.0, "T-Shirt"),
                ProductDomain(4, R.drawable.img_product_4, 40000.0, 1.0, "Lego"),
                ProductDomain(5, R.drawable.img_product_5, 60000.0, 4.0, "Necklace"),
                ProductDomain(6, R.drawable.img_product_6, 100000.0, 90.0, "Shirt"),
                ProductDomain(7, R.drawable.img_product_7, 220000.0, 101.0, "Light"),
                ProductDomain(8, R.drawable.img_product_2, 1000000.0, 120.0, "Nail"),
                ProductDomain(9, R.drawable.img_product_5, 90000.0, 22.0, "Necklace"),
                ProductDomain(10, R.drawable.img_product_1, 300000.0, 15.0, "Ring"),
                ProductDomain(11, R.drawable.img_product_3, 40000.0, 5.0, "T-Shirt"),
                ProductDomain(12, R.drawable.img_product_4, 80000.0, 89.0, "Lego"),
            )
        )
    }
}