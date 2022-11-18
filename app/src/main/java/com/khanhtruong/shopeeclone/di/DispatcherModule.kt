package com.khanhtruong.shopeeclone.di

import com.khanhtruong.shopeeclone.util.dispatcher.DispatcherProvider
import com.khanhtruong.shopeeclone.util.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcher(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}