package com.example.warhammer40kapp.di

import com.example.warhammer40kapp.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNetworkRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    @ViewModelScoped
    abstract fun provideCacheRepository(
       cacheRepositoryImpl: CacheRepositoryImpl
    ): CacheRepository

    @Binds
    @ViewModelScoped
    abstract fun provideMainRepository(
       mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

//    @Provides
//    @IoDispatcher
//    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
