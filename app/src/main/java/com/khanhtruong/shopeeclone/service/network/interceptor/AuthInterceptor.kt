package com.khanhtruong.shopeeclone.service.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        request.newBuilder()
            .addHeader("Authentication", "Bearer <token>")

        return chain.proceed(request)
    }
}