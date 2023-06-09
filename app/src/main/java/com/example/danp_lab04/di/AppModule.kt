package com.example.danp_lab04.di

import android.content.Context
import androidx.room.Room
import com.example.danp_lab04.model.AppDatabase
import com.example.danp_lab04.model.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(context,
        AppDatabase::class.java,
        "country.db"
    ).build()

    @Provides
    fun provideCountryDao(
        appDatabase: AppDatabase
    ) = appDatabase.countryDao()

    @Provides
    fun provideCountryRepository(
        appDatabase: AppDatabase
    ) = CountryRepository(
        appDatabase = appDatabase
    )
}