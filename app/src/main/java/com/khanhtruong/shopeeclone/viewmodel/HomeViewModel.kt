package com.khanhtruong.shopeeclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.khanhtruong.shopeeclone.data.domain.ProductDomain
import com.khanhtruong.shopeeclone.data.model.ProductData
import com.khanhtruong.shopeeclone.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 15

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepo: ProductRepo
) : ViewModel() {

    val productItems: Flow<PagingData<ProductData>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { productRepo.getProductPagingSource() }
    ).flow.cachedIn(viewModelScope)
}