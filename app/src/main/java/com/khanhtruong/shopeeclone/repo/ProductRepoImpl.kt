package com.khanhtruong.shopeeclone.repo

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.Resource
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import com.khanhtruong.shopeeclone.data.model.ProductData
import com.khanhtruong.shopeeclone.di.qualifier.MockProductProvider
import com.khanhtruong.shopeeclone.di.qualifier.ProductProvider
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import com.khanhtruong.shopeeclone.service.safeCallApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Integer.max
import javax.inject.Inject
import kotlin.random.Random

class ProductRepoImpl @Inject constructor(
    @ProductProvider private val apiProduct: ProductAPI
) : ProductRepo {
    override fun getProductDataSet(page: Int): Flow<Resource<List<ProductDomain>>> {
        return flow {
            val data = safeCallApi {
                apiProduct.getProductDataSet()
            }
            emit(data)
        }
    }

    override fun getProductPagingSource() = ProductPagingSource()
}

class ProductPagingSource : PagingSource<Int, ProductData>() {
    private val STARTING_KEY = 0

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = (article.id ?: 0) - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)
        Log.d("Debuggg", "Start: $start, Range: $range")

        delay(300)

        return LoadResult.Page(
            data = range.map { number ->
                ProductData(
                    // Generate consecutive increasing numbers as the article id
                    id = number,
                    title = "Product $number",
                    snapshot = randomSnapshot(),
                    price = randomPrice(),
                    sold = randomSold(),
                )
            },

            // Make sure we don't try to load items behind the STARTING_KEY
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
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

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

}