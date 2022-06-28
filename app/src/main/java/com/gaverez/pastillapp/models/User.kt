package com.gaverez.pastillapp.models

import java.util.*

data class User(
    val id: Long?,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val birth: Date
)