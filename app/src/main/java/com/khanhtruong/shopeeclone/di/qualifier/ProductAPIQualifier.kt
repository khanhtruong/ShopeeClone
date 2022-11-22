package com.khanhtruong.shopeeclone.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductAPIProvider

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockProductAPIProvider