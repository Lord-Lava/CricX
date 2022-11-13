package com.lava.cricx.di

import com.lava.cricx.data.error.mapper.ErrorMapper
import com.lava.cricx.data.error.mapper.ErrorMapperSource
import com.lava.cricx.usecase.errors.ErrorManager
import com.lava.cricx.usecase.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
