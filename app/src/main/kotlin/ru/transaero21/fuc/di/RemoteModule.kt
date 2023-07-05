package ru.transaero21.fuc.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import ru.transaero21.fuc.remote.IMzApi
import ru.transaero21.fuc.remote.MzApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface IRemoteModule {
        @Binds
        @Singleton
        fun provideIMzApi(repository: MzApi): IMzApi
    }
}
