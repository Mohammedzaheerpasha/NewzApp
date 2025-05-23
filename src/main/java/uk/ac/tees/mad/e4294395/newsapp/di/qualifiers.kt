package uk.ac.tees.mad.e4294395.newsapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName