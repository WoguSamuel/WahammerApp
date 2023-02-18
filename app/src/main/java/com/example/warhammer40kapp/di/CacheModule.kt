package com.example.warhammer40kapp.di

import android.content.Context
import androidx.room.Room
import com.example.warhammer40kapp.cache.WarhammerDAO
import com.example.warhammer40kapp.cache.WarhammerDatabase
import com.example.warhammer40kapp.repository.CacheRepository
import com.example.warhammer40kapp.repository.CacheRepositoryImpl
import com.example.warhammer40kapp.repository.NetworkRepository
import com.example.warhammer40kapp.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext appContext: Context): WarhammerDatabase =
        Room.databaseBuilder(
            appContext,
            WarhammerDatabase::class.java,
            "warhammer-db"
        ).build()

    @Provides
    @Singleton
    fun providesRoomDAO(database: WarhammerDatabase): WarhammerDAO =
        database.warhammerDAO()

    @Provides
    @IoDispatcher
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}