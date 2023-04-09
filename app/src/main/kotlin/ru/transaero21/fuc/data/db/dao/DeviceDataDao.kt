package ru.transaero21.fuc.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.transaero21.fuc.data.db.deviceDataTable
import ru.transaero21.fuc.entity.model.DeviceData

@Dao
interface DeviceDataDao {
    @Query("SELECT * FROM $deviceDataTable")
    fun getAll(): List<DeviceData>

    @Insert
    fun insertAll(vararg device: DeviceData)

    @Delete
    fun delete(device: DeviceData)
}