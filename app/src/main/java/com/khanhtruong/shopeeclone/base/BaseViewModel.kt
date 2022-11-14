package com.khanhtruong.shopeeclone.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khanhtruong.shopeeclone.data.AppError
import com.khanhtruong.shopeeclone.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(private val screenState: ScreenState) : ViewModel() {
    fun emitError(error: AppError) {
        viewModelScope.launch {
            screenState.emitError(error)
        }
    }

    fun emitLoading(showLoading: Boolean) {
        viewModelScope.launch {
            screenState.emitLoading(showLoading)
        }
    }
}

fun <T> BaseViewModel.launchAPI(
    flow: Flow<Resource<T>>,
    onSuccess: (data: T) -> Unit,
    onFailure: ((error: AppError) -> Unit)? = null,
    showLoading: Boolean = true,
) {
    viewModelScope.launch {
        emitLoading(showLoading)
        flow.collect {
            emitLoading(false)
            when (it) {
                is Resource.Success -> {
                    onSuccess.invoke(it.data)
                }
                is Resource.Failure -> {
                    if (onFailure == null) {
                        emitError(it.appError)
                    } else {
                        onFailure.invoke(it.appError)
                    }
                }
            }
        }
    }
}