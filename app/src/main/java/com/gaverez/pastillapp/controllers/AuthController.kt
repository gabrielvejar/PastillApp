package com.gaverez.pastillapp.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.room.Room
import com.gaverez.pastillapp.ListActivity
import com.gaverez.pastillapp.LoginActivity
import com.gaverez.pastillapp.MainActivity
import com.gaverez.pastillapp.lib.AppDatabase
import com.gaverez.pastillapp.models.User
import com.gaverez.pastillapp.models.UserEntity
import com.gaverez.pastillapp.utils.BCrypt

class AuthController constructor(ctx: Context) {
    //Shared Preferences
    private val sharedPref = ctx.getSharedPreferences("MEDICAMENT_APP", Context.MODE_PRIVATE)

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

            //Guardar el user id en shared preferences
            val sharedEdit = sharedPref.edit()
            sharedEdit.putLong("user_id", user.id!!)
            sharedEdit.commit()

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

    fun checkUserSession() {
        //Obtener el user id guardado en shared preferences
        val id = sharedPref.getLong("user_id", -1)

        Handler().postDelayed({
            //Validar user id
            if (id == (-1).toLong()) {
                //Si no existe redirecciona a la pantalla de bienvenida (login y registro)
                val intent = Intent(this.ctx, MainActivity::class.java)
                this.ctx.startActivity(intent)
            } else {
                //Si exite redirecciona al listado de medicamentos (main)
                val intent = Intent(this.ctx, ListActivity::class.java)
                this.ctx.startActivity(intent)
            }
            //Elimina anteriores activities del stack
            (this.ctx as Activity).finish()
        }, 2000)
    }

    fun clearSession() {
        //Limpia el user id del shared preferences
        val editor = sharedPref.edit()
        editor.remove("user_id")
        editor.commit()
        //Redirecciona al login
        val intent = Intent(this.ctx, MainActivity::class.java)
        this.ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }

}