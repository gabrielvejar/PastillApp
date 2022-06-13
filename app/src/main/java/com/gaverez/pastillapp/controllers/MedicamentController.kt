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
                    id = i.toLong(),
                    name =  "Medicamento $i",
                    dateStart = "2022/06/1${i-1}",
                    timeStart = "1${i-1}:00",
                    days = i,
                    repeatQty = i+2,
                    repeatUnit = "días",
                    note = "Antibiotico para la infección del oído."
                )
            )
        }
        return medicaments
    }
}