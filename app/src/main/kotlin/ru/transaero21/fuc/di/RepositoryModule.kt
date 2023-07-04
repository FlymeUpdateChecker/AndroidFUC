package ru.transaero21.fuc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.transaero21.fuc.data.repo.check.CheckRepository
import ru.transaero21.fuc.data.repo.check.ICheckRepository
import ru.transaero21.fuc.data.repo.devices.DevicesRepository
import ru.transaero21.fuc.data.repo.devices.IDevicesRepository
import ru.transaero21.fuc.data.repo.settings.ISettingsRepository
import ru.transaero21.fuc.data.repo.settings.SettingsRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideISettingsRepository(repository: SettingsRepository): ISettingsRepository

    @Binds
    fun provideIDevicesRepository(repository: DevicesRepository): IDevicesRepository

    @Binds
    fun provideICheckRepository(repository: CheckRepository): ICheckRepository
}