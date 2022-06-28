package com.gaverez.pastillapp.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaverez.pastillapp.dao.MedicamentDAO
import com.gaverez.pastillapp.dao.UserDAO
import com.gaverez.pastillapp.models.MedicamentEntity
import com.gaverez.pastillapp.models.UserEntity
import com.gaverez.pastillapp.utils.Converters

@Database(entities = [UserEntity::class, MedicamentEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun medicamentDao(): MedicamentDAO

    companion object {
        const val DATABASE_NAME = "medicament-app"
    }
}