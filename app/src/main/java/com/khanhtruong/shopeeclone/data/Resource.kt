package com.khanhtruong.shopeeclone.data

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failure<T>(val error: ErrorType) : Resource<T>()

    fun isSuccess() = this is Success

    fun isError() = this is Failure

    fun value(): T? {
        return when (this) {
            is Success -> this.data
            else -> null
        }
    }

    fun error(): ErrorType? {
        return when (this) {
            is Failure -> this.error
            else -> null
        }
    }
}