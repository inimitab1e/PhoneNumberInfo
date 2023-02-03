package com.example.phonenumberinfo.presentation.di

import com.example.phonenumberinfo.data.network.ApiService
import com.example.phonenumberinfo.data.repository.GetInfoRepositoryImpl
import com.example.phonenumberinfo.data.utils.AppDispatchers
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetInfoRepository(client: Retrofit): GetInfoRepository {
        return GetInfoRepositoryImpl(
            apiService = client.create(ApiService::class.java),
            dispatchers = AppDispatchers()
        )
    }
}