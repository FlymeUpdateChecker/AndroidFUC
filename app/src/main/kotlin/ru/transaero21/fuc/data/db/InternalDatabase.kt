package ru.transaero21.fuc.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.transaero21.fuc.data.db.dao.DeviceDataDao
import ru.transaero21.fuc.entity.model.DeviceData

const val deviceDataTable = "device_table"

@Database(entities = [DeviceData::class], version = 1, exportSchema = false)
abstract class InternalDatabase : RoomDatabase() {
    abstract fun deviceDataDao(): DeviceDataDao
}