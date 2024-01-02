package com.example.realmlessons.di

import com.example.realmlessons.domain.usecase.FetchAllCloud
import com.example.realmlessons.domain.usecase.FetchAllCloudImpl
import com.example.realmlessons.domain.usecase.FetchSavedCameraUseCase
import com.example.realmlessons.domain.usecase.FetchSavedCameraUseCaseImpl
import com.example.realmlessons.domain.usecase.ISCameraSavedImpl
import com.example.realmlessons.domain.usecase.IsCameraSaveUseCase
import com.example.realmlessons.domain.usecase.IsCameraSaveUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsFetchAllSaver(
        implementation: ISCameraSavedImpl
    ): IsCameraSaveUseCase

    @Binds
    fun bindsFetchAllCloud(
        implementation: FetchAllCloudImpl
    ): FetchAllCloud

    @Binds
    fun bindsFetchSavedCameraUseCaseImpl(
        implementation: FetchSavedCameraUseCaseImpl
    ): FetchSavedCameraUseCase

}