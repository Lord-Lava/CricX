package com.lava.cricx.di

import com.lava.cricx.data.remote.source.PlayersData
import com.lava.cricx.data.remote.source.PlayersDataSource
import com.lava.cricx.data.repository.PlayersRepositoryImpl
import com.lava.cricx.domain.repository.PlayersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun providePlayersRepository(playersRepository: PlayersRepositoryImpl): PlayersRepository

    @Binds
    abstract fun providePlayersDataSource(playersData: PlayersData): PlayersDataSource
}