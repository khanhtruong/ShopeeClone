package com.khanhtruong.shopeeclone.base

import com.khanhtruong.shopeeclone.data.AppError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ScreenState {
    private val _errorsFlow = MutableSharedFlow<AppError>()
    private val _loadingFlow = MutableSharedFlow<Boolean>()
    val errorsFlow = _errorsFlow.asSharedFlow()
    val loadingFlow = _loadingFlow.asSharedFlow()

    suspend fun emitError(error: AppError) {
        _errorsFlow.emit(error)
    }

    suspend fun emitLoading(showLoading: Boolean) {
        _loadingFlow.emit(showLoading)
    }
}