package com.khanhtruong.shopeeclone.di

import com.google.gson.GsonBuilder
import com.khanhtruong.shopeeclone.BuildConfig
import com.khanhtruong.shopeeclone.di.qualifier.AuthOkhttpInterceptorClient
import com.khanhtruong.shopeeclone.di.qualifier.AuthRetrofit
import com.khanhtruong.shopeeclone.di.qualifier.DefaultOkhttpInterceptorClient
import com.khanhtruong.shopeeclone.di.qualifier.DefaultRetrofit
import com.khanhtruong.shopeeclone.service.network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    @DefaultRetrofit
    fun defaultRetrofitProvider(
        @DefaultOkhttpInterceptorClient client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun authRetrofitProvider(
        @AuthOkhttpInterceptorClient client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun gsonFactoryProvider(): GsonConverterFactory {
        return GsonConverterFactory
            .create(
                GsonBuilder()
                    .setLenient()
                    .disableHtmlEscaping()
                    .create()
            )
    }

    @Provides
    @Singleton
    @DefaultOkhttpInterceptorClient
    fun defaultOkhttpProvider(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .connectTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }
        return httpBuilder.build()
    }

    @Provides
    @Singleton
    @AuthOkhttpInterceptorClient
    fun authOkhttpProvider(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder
            .connectTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_TIME_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }
        return httpBuilder.build()
    }

    companion object {
        const val TIMEOUT_TIME_IN_SECONDS = 10L
        const val API_BASE_PATH = "https://example.com/"
    }
}