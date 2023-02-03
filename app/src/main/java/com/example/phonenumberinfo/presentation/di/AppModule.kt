package com.example.phonenumberinfo.presentation.di

import com.example.phonenumberinfo.data.utils.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object AppModule {

    @Provides
    @Singleton
    fun provideDispatchers() : AppDispatchers = AppDispatchers()
}