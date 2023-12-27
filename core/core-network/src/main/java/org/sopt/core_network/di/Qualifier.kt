package org.sopt.core_network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoneOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkHttpClient
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LinkMindRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthLinkMindRetrofit
