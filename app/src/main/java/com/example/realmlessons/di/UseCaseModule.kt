package com.example.realmlessons.di

import com.example.realmlessons.domain.usecase.FetchAllCloud
import com.example.realmlessons.domain.usecase.FetchAllCloudImpl
import com.example.realmlessons.domain.usecase.FetchSavedCameraUseCase
import com.example.realmlessons.domain.usecase.FetchSavedCameraUseCaseImpl
import com.example.realmlessons.domain.usecase.CameraSaveOrDeleteUseCase
import com.example.realmlessons.domain.usecase.CameraSaveOrDeleteUseCaseImpl
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
        implementation: FetchAllCloudImpl
    ): FetchAllCloud

    @Binds
    fun bindsFetchSavedCameraUseCaseImpl(
        implementation: FetchSavedCameraUseCaseImpl
    ): FetchSavedCameraUseCase

}