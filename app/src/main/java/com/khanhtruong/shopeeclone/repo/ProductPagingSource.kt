package com.khanhtruong.shopeeclone.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khanhtruong.shopeeclone.data.Resource
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import com.khanhtruong.shopeeclone.data.message
import com.khanhtruong.shopeeclone.data.model.ProductData
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import com.khanhtruong.shopeeclone.service.safeCallApi
import kotlinx.coroutines.delay
import retrofit2.Response

class ProductPagingSource(
    private val apiProduct: ProductAPI,
) : PagingSource<Int, ProductData>() {
    private val STARTING_KEY = 0

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize

        val resource = safeCallApi {
            apiProduct.getProductDataSet(start, params.loadSize)
        }

        delay(300)

        when (resource) {
            is Resource.Failure -> {
                return LoadResult.Error(throwable = Throwable(resource.error.message()))
            }
            is Resource.Success -> {
                return LoadResult.Page(
                    // Faking the dataset
                    data = resource.data.map {
                        it.toProductData()
                    },
                    // Make sure we don't try to load items behind the STARTING_KEY
                    prevKey = when (start) {
                        STARTING_KEY -> null
                        else -> ensureValidKey(
                            key = (resource.data.firstOrNull()?.id
                                ?: STARTING_KEY) - 1
                        )
                    },
                    nextKey = ensureValidKey(key = (resource.data.lastOrNull()?.id ?: STARTING_KEY))
                )
            }
        }
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = Integer.max(STARTING_KEY, key)
}