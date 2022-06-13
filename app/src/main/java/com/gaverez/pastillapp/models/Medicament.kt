package com.gaverez.pastillapp.models

import java.io.Serializable

data class Medicament(
    val id: Long? = null,
    val active: Boolean = true,
    val name: String,
    val dateStart: String,
    val timeStart: String,
    val days: Int,
    val repeatQty: Int,
    val repeatUnit: String,
    val note: String? = null,
) : Serializable
