package ru.transaero21.fuc.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.transaero21.fuc.data.db.InternalDatabase
import ru.transaero21.fuc.data.db.dao.DeviceDataDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDeviceDataDao(db: InternalDatabase): DeviceDataDao {
        return db.deviceDataDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): InternalDatabase {
        return Room.databaseBuilder(context, InternalDatabase::class.java, "FUC").build()
    }
}