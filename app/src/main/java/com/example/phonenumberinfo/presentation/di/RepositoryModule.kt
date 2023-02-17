package com.example.phonenumberinfo.presentation.di

import com.example.phonenumberinfo.data.network.ApiService
import com.example.phonenumberinfo.data.repository.GetInfoRepositoryImpl
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetInfoRepository(
        apiService: ApiService,
        coroutineDispatcher: CoroutineDispatcher
    ): GetInfoRepository {
        return GetInfoRepositoryImpl(
            apiService = apiService,
            ioDispatcher = coroutineDispatcher
        )
    }
}