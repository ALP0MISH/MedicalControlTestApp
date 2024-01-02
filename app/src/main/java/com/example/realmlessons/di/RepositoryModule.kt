package com.example.realmlessons.di

import com.example.realmlessons.data.repository.CameraAndDoorRepositoryImpl
import com.example.realmlessons.domain.repository.CameraAndDoorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsUserRegisterRepository(
        implementation: CameraAndDoorRepositoryImpl
    ): CameraAndDoorRepository

}