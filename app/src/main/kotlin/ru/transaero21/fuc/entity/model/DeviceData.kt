package ru.transaero21.fuc.entity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.transaero21.fuc.data.db.deviceDataTable
import ru.transaero21.fuc.entity.enums.Android
import ru.transaero21.fuc.entity.enums.Devices
import ru.transaero21.fuc.entity.enums.Type
import ru.transaero21.fuc.remote.DEFAULT_HOST

@Entity(tableName = deviceDataTable)
data class DeviceData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "codename") val codename: String = Devices.Meizu20Pro.codename,
    @ColumnInfo(name = "imei") val imei: String = "",
    @ColumnInfo(name = "sn") val sn: String = "",
    @ColumnInfo(name = "android") val android: String = Android.Tiramisu.version,
    @ColumnInfo(name = "type") val type: String = Type.Stable.type,
    @ColumnInfo(name = "timestamp") val timestamp: String = (System.currentTimeMillis() / 1000L).toString(),
    @ColumnInfo(name = "host") val host: String = DEFAULT_HOST
)
