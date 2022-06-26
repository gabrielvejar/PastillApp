package com.gaverez.pastillapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "first_name") val firstname: String,
    @ColumnInfo(name = "last_name") val lastname: String,
    val email: String,
    val password: String,
    val birth: Date
)