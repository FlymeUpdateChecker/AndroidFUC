package ru.transaero21.fuc.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.transaero21.fuc.data.db.deviceDataTable
import ru.transaero21.fuc.entity.model.DeviceData

@Dao
interface DeviceDataDao {
    @Query("SELECT * FROM $deviceDataTable")
    fun getAll(): Flow<List<DeviceData>>

    @Query("SELECT * FROM $deviceDataTable WHERE id= :id")
    fun getById(id: Int): DeviceData?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg device: DeviceData)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(vararg device: DeviceData)

    @Delete
    fun delete(device: DeviceData)
}