package com.khanhtruong.shopeeclone.di

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
    abstract fun bindProductRepo(productRepoImpl: ProductRepoImpl): ProductRepo
}