package com.khanhtruong.shopeeclone.di

import com.khanhtruong.shopeeclone.di.qualifier.DefaultProductRepoProvider
import com.khanhtruong.shopeeclone.di.qualifier.MockProductRepoProvider
import com.khanhtruong.shopeeclone.repo.MockProductRepoImpl
import com.khanhtruong.shopeeclone.repo.ProductRepo
import com.khanhtruong.shopeeclone.repo.ProductRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @DefaultProductRepoProvider
    abstract fun bindProductRepo(productRepoImpl: ProductRepoImpl): ProductRepo

    @Binds
    @MockProductRepoProvider
    abstract fun bindMockProductRepo(productRepoImpl: MockProductRepoImpl): ProductRepo
}