package com.khanhtruong.shopeeclone.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultProductRepoProvider

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockProductRepoProvider