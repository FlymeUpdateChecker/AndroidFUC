package ru.transaero21.fuc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.transaero21.fuc.vm.check.CheckViewModel
import ru.transaero21.fuc.vm.check.ICheckViewModel
import ru.transaero21.fuc.vm.create.CreateViewModel
import ru.transaero21.fuc.vm.create.ICreateViewModel
import ru.transaero21.fuc.vm.device.DeviceViewModel
import ru.transaero21.fuc.vm.device.IDeviceViewModel
import ru.transaero21.fuc.vm.devices.DevicesViewModel
import ru.transaero21.fuc.vm.devices.IDevicesViewModel
import ru.transaero21.fuc.vm.settings.ISettingsViewModel
import ru.transaero21.fuc.vm.settings.SettingsViewModel
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @Singleton
    fun provideICreateViewModel(viewModel: CreateViewModel): ICreateViewModel

    @Binds
    @Singleton
    fun provideIDevicesViewModel(viewModel: DevicesViewModel): IDevicesViewModel

    @Binds
    @Singleton
    fun provideIDeviceViewModel(viewModel: DeviceViewModel): IDeviceViewModel

    @Binds
    @Singleton
    fun provideISettingsViewModel(viewModel: SettingsViewModel): ISettingsViewModel

    @Binds
    @Singleton
    fun provideICheckViewModel(viewModel: CheckViewModel): ICheckViewModel
}