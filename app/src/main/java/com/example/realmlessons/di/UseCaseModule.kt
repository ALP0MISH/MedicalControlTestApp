package com.example.realmlessons.di

import com.example.realmlessons.domain.usecase.CameraSaveOrDeleteUseCase
import com.example.realmlessons.domain.usecase.CameraSaveOrDeleteUseCaseImpl
import com.example.realmlessons.domain.usecase.FetchCameraAndDoorUseCase
import com.example.realmlessons.domain.usecase.FetchCameraAndDoorUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsCameraSaveOrDeleteUseCase(
        implementation: CameraSaveOrDeleteUseCaseImpl
    ): CameraSaveOrDeleteUseCase

    @Binds
    fun bindsFetchAllCloud(
        implementation: FetchCameraAndDoorUseCaseImpl
    ): FetchCameraAndDoorUseCase
}