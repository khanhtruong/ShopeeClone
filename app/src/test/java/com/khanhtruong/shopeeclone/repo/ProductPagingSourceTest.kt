package com.khanhtruong.shopeeclone.repo

import androidx.paging.PagingSource
import com.khanhtruong.shopeeclone.R
import com.khanhtruong.shopeeclone.data.model.ProductData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import testcommon.khanhtruong.shopeeclone.repo.FakeProductAPI
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductPagingSourceTest {

    private val fakeProducts = listOf(
        ProductData(1, R.drawable.img_product_1, 10000.0, 20.0, "Ring"),
        ProductData(2, R.drawable.img_product_2, 20000.0, 2.0, "Nail"),
        ProductData(3, R.drawable.img_product_3, 30000.0, 10.0, "T-Shirt"),
        ProductData(4, R.drawable.img_product_4, 40000.0, 1.0, "Lego"),
        ProductData(5, R.drawable.img_product_5, 60000.0, 4.0, "Necklace"),
    )
    private val fakeProductAPI = FakeProductAPI().apply {
        fakeProducts.forEach { addProduct(it.toProductDomain()) }
    }

    @Test
    fun `given fakeProductAPI return success, when productPagingSource load the first page(null key), then productPagingSource response with the first two records`() =
        runTest {
            val pagingSource = ProductPagingSource(fakeProductAPI)
            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(fakeProducts[0], fakeProducts[1]),
                    prevKey = null,
                    nextKey = fakeProducts[1].id,
                ),
                actual = pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ),
            )
        }

    @Test
    fun `given fakeProductAPI return success, when productPagingSource load the second page(key equal 1), then productPagingSource response the next two records`() =
        runTest {
            val pagingSource = ProductPagingSource(fakeProductAPI)
            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(fakeProducts[2], fakeProducts[3]),
                    prevKey = fakeProducts[1].id,
                    nextKey = fakeProducts[3].id,
                ),
                actual = pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = fakeProducts[1].id,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ),
            )
        }
}