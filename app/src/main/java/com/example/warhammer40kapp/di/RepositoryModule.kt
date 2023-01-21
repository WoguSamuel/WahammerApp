package com.example.warhammer40kapp.di

import com.example.warhammer40kapp.repository.CacheRepository
import com.example.warhammer40kapp.repository.CacheRepositoryImpl
import com.example.warhammer40kapp.repository.NetworkRepository
import com.example.warhammer40kapp.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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
}
