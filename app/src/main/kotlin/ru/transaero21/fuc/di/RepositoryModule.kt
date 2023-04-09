package ru.transaero21.fuc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.transaero21.fuc.data.repo.settings.ISettingsRepository
import ru.transaero21.fuc.data.repo.settings.SettingsRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideSettingsRepository(repository: SettingsRepository): ISettingsRepository
}