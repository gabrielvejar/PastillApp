package com.gaverez.pastillapp.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.gaverez.pastillapp.ListActivity
import com.gaverez.pastillapp.LoginActivity
import com.gaverez.pastillapp.lib.AppDatabase
import com.gaverez.pastillapp.models.User
import com.gaverez.pastillapp.models.UserEntity
import com.gaverez.pastillapp.utils.BCrypt

class AuthController constructor(ctx: Context) {
    private val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
    private val ctx = ctx
    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .userDao()

    fun login(email: String, password: String) {
        val user = dao.findByEmail(email)

        if (user == null) {
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
            return
        }
        if (BCrypt.checkpw(password, user.password)) {
            Toast.makeText(this.ctx, "Bienvenido ${user.firstname}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, ListActivity::class.java)
            this.ctx.startActivity(intent)
            (this.ctx as Activity).finish()
        } else {
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
        }
    }

    fun register(user: User) {
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val userEntity = UserEntity(
            id = null,
            firstname = user.firstname,
            lastname = user.lastname,
            email = user.email,
            password = hashedPassword,
            birth = user.birth
        )

        try {
            dao.insert(userEntity)

            Toast.makeText(this.ctx, "Cuenta registrada", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            this.ctx.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this.ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
        }

    }
}