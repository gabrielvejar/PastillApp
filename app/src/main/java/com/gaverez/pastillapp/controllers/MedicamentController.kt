package com.gaverez.pastillapp.controllers

import android.content.Context
import androidx.room.Room
import com.gaverez.pastillapp.lib.AppDatabase
import com.gaverez.pastillapp.models.Medicament

class MedicamentController constructor(ctx: Context, userId: Long = 0) {
    private val ctx = ctx
    private val userId = userId
    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .medicamentDao()

    fun getAll (): List<Medicament> {
        val medicamentEntities = dao.findAll(userId)

        val medicaments = ArrayList<Medicament>()

        medicamentEntities.forEach { med -> medicaments.add(Medicament(
            id = med.id,
            active = med.active,
            name = med.name,
            dateStart = med.dateStart,
            timeStart = med.timeStart,
            days = med.days,
            repeatQty = med.repeatQty,
            repeatUnit = med.repeatUnit,
            note = med.note,
        )) }

        return medicaments
    }
}