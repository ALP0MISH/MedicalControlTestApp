package com.example.realmlessons.di

import com.example.realmlessons.data.cloud.service.CameraAndDoorService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://cars.cprogroup.ru/"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val reguest = chain.request()
                            .newBuilder()
                            .build()
                        return@Interceptor chain.proceed(request = reguest)
                    }).build()
            ).build()
    }

    @Provides
    fun provideCameraAndDoorService(retrofit: Retrofit): CameraAndDoorService =
        retrofit.create(CameraAndDoorService::class.java)
}