package com.example.realmlessons.di

import android.content.Context
import androidx.room.Room
import com.example.realmlessons.data.cashe.dao.CommonDao
import com.example.realmlessons.data.cashe.database.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val MOVIE_DATABASE_NAME = "common_database"

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): DataBase = Room.databaseBuilder(context, DataBase::class.java, MOVIE_DATABASE_NAME).build()

    @Provides
    fun provideMovieDao(
        dataBase: DataBase
    ): CommonDao = dataBase.commonDao()
}