package com.gaverez.pastillapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicaments")
data class MedicamentEntity (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val active: Boolean = true,
    val name: String,
    @ColumnInfo(name = "date_start") val dateStart: String,
    @ColumnInfo(name = "time_start") val timeStart: String,
    val days: Int,
    @ColumnInfo(name = "repeat_qty") val repeatQty: Int,
    @ColumnInfo(name = "repeat_unit") val repeatUnit: String,
    val note: String? = null,
    @ColumnInfo(name = "user_id") val userId: Long,
)