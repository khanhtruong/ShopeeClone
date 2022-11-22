package testcommon.khanhtruong.shopeeclone.repo

import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import retrofit2.Response

class FakeProductAPI : ProductAPI {
    private var products = mutableListOf<ProductDomain>()

    fun addProduct(product: ProductDomain) {
        products.add(product)
    }

    override suspend fun getProductDataSet(after: Int, limit: Int): Response<List<ProductDomain>> {
        val resp = products.filter {
            after < (it.id ?: 0)
        }.take(limit)
        return Response.success(resp)
    }
}