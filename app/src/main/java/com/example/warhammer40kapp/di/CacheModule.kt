package com.example.warhammer40kapp.di

import android.content.Context
import androidx.room.Room
import com.example.warhammer40kapp.cache.WarhammerDAO
import com.example.warhammer40kapp.cache.WarhammerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}