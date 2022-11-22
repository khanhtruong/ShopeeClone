package com.khanhtruong.shopeeclone.di

import com.khanhtruong.shopeeclone.di.qualifier.AuthRetrofit
import com.khanhtruong.shopeeclone.di.qualifier.MockProductAPIProvider
import com.khanhtruong.shopeeclone.di.qualifier.ProductAPIProvider
import com.khanhtruong.shopeeclone.service.network.MockProductAPI
import com.khanhtruong.shopeeclone.service.network.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @ProductAPIProvider
    fun productProvider(@AuthRetrofit retrofit: Retrofit): ProductAPI {
        return retrofit.create(ProductAPI::class.java)
    }

    @Provides
    @MockProductAPIProvider
    fun productMockProvider(): ProductAPI {
        return MockProductAPI()
    }

}