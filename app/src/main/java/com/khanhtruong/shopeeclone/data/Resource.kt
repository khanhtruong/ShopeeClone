package com.khanhtruong.shopeeclone.data

sealed interface Resource<out T> {
    class Success<T>(val data: T) : Resource<T>
    class Failure(val error: ErrorType) : Resource<Nothing>

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

inline fun <T, P : Any> Resource<T>.map(block: (T) -> P): Resource<P> {
    return when (this) {
        is Resource.Success -> Resource.Success(block(this.data))
        is Resource.Failure -> this
    }
}

inline fun <T> Resource<T>.doOnSuccess(block: (T) -> Unit): Resource<T> {
    when (this) {
        is Resource.Success -> {
            block.invoke(this.data)
        }
    }
    return this
}

inline fun <T> Resource<T>.doOnError(block: (ErrorType) -> Unit): Resource<T> {
    when (this) {
        is Resource.Failure -> {
            block.invoke(this.error)
        }
    }
    return this
}
