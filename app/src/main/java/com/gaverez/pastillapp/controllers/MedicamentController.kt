package com.gaverez.pastillapp.controllers

import android.content.Context
import com.gaverez.pastillapp.models.Medicament

class MedicamentController constructor(ctx: Context){
    private val ctx = ctx

    fun getAll (): List<Medicament> {
        val medicaments = ArrayList<Medicament>()
        for (i in 1..10) {
            medicaments.add(
                Medicament(
                name =  "Medicamento $i",
                dateStart = "2022/06/11",
                timeStart = "22:00",
                repeatQty = 5,
                repeatUnit = "day",
            )
            )
        }
        return medicaments
    }
}