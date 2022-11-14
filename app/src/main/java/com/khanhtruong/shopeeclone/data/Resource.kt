package com.khanhtruong.shopeeclone.data

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failure<T>(val appError: AppError) : Resource<T>()
}