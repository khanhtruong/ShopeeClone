package com.khanhtruong.shopeeclone.service.network

import androidx.annotation.DrawableRes
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import retrofit2.Response
import kotlin.random.Random

class MockProductAPI : ProductAPI {
    override suspend fun getProductDataSet(after: Int, limit: Int): Response<List<ProductDomain>> {
        val range = after.until(after + limit)
        return Response.success(
            range.map {
                ProductDomain(
                    // Generate consecutive increasing numbers as the article id
                    id = it,
                    title = "Product $it",
                    snapshot = randomSnapshot(),
                    price = randomPrice(),
                    sold = randomSold(),
                )
            }
        )
    }

    @DrawableRes
    private fun randomSnapshot(): Int {
        return when (Random.nextInt(1, 8)) {
            1 -> R.drawable.img_product_1
            2 -> R.drawable.img_product_2
            3 -> R.drawable.img_product_3
            4 -> R.drawable.img_product_4
            5 -> R.drawable.img_product_5
            6 -> R.drawable.img_product_6
            else -> R.drawable.img_product_7
        }
    }

    private fun randomPrice(): Double {
        return Random.nextInt(100000).toDouble()
    }

    private fun randomSold(): Double {
        return Random.nextInt(100).toDouble()
    }

}