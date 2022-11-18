package com.khanhtruong.shopeeclone.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductProvider

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockProductProvider