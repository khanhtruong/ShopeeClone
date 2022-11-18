package com.khanhtruong.shopeeclone.di

import com.khanhtruong.shopeeclone.di.qualifier.AuthRetrofit
import com.khanhtruong.shopeeclone.di.qualifier.MockProductProvider
import com.khanhtruong.shopeeclone.di.qualifier.ProductProvider
import com.khanhtruong.shopeeclone.service.network.MockProductAPI
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @ProductProvider
    fun productProvider(@AuthRetrofit retrofit: Retrofit): ProductAPI {
        return retrofit.create(ProductAPI::class.java)
    }

    @Provides
    @MockProductProvider
    fun productMockProvider(): ProductAPI {
        return MockProductAPI()
    }

}