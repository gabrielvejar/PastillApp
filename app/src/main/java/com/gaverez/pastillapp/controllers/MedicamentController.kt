package com.gaverez.pastillapp.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.gaverez.pastillapp.ListActivity
import com.gaverez.pastillapp.lib.AppDatabase
import com.gaverez.pastillapp.models.Medicament
import com.gaverez.pastillapp.models.MedicamentEntity

class MedicamentController constructor(ctx: Context) {
    //Shared Preferences
    private val sharedPref = ctx.getSharedPreferences("MEDICAMENT_APP", Context.MODE_PRIVATE)

    private val ctx = ctx
    private val userId = sharedPref.getLong("user_id", -1)
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

    fun getById(id: Long): Medicament? {
        val med = dao.findById(id) ?: return null

        return Medicament(
            id = med?.id,
            active = med.active,
            name = med.name,
            dateStart = med.dateStart,
            timeStart = med.timeStart,
            days = med.days,
            repeatQty = med.repeatQty,
            repeatUnit = med.repeatUnit,
            note = med.note,
        )
    }

    fun create (med: Medicament) {
        val entity = MedicamentEntity(
            id = null,
            active = med.active,
            name = med.name,
            dateStart = med.dateStart,
            timeStart = med.timeStart,
            days = med.days,
            repeatQty = med.repeatQty,
            repeatUnit = med.repeatUnit,
            note = med.note,
            userId = sharedPref.getLong("user_id", -1)
        )
        dao.insert(entity)

        val intent = Intent(ctx, ListActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }


    fun update (med: Medicament) {
        val entity = MedicamentEntity(
            id = med.id,
            active = med.active,
            name = med.name,
            dateStart = med.dateStart,
            timeStart = med.timeStart,
            days = med.days,
            repeatQty = med.repeatQty,
            repeatUnit = med.repeatUnit,
            note = med.note,
            userId = sharedPref.getLong("user_id", -1)
        )
        dao.update(entity)

        val intent = Intent(ctx, ListActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }

    fun delete (id: Long) {
        dao.delete(id)
        val intent = Intent(ctx, ListActivity::class.java)
        ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
}