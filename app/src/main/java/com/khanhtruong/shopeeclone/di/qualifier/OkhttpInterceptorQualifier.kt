package com.khanhtruong.shopeeclone.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkhttpInterceptorClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkhttpInterceptorClient