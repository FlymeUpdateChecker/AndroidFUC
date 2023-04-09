package ru.transaero21.fuc.entity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.transaero21.fuc.data.db.deviceDataTable

@Entity(tableName = deviceDataTable)
data class DeviceData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "codename") val codename: String,
    @ColumnInfo(name = "imei") val imei: String,
    @ColumnInfo(name = "sn") val sn: String,
    @ColumnInfo(name = "android") val android: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "timestamp") val timestamp: Int,
    @ColumnInfo(name = "host") val host: String
)
