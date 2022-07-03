package com.gaverez.pastillapp.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.gaverez.pastillapp.ListActivity
import com.gaverez.pastillapp.LoginActivity
import com.gaverez.pastillapp.MainActivity
import com.gaverez.pastillapp.lib.AppDatabase
import com.gaverez.pastillapp.lib.RetrofitClient
import com.gaverez.pastillapp.models.*
import com.gaverez.pastillapp.services.AuthService

import retrofit2.Callback;
import retrofit2.Call
import retrofit2.Response

class AuthController constructor(ctx: Context) {
    //Shared Preferences
    private val sharedPref = ctx.getSharedPreferences("MEDICAMENT_APP", Context.MODE_PRIVATE)

    private val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
    private val ctx = ctx

    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val authService = retrofit.create(AuthService::class.java)

    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .userDao()

    fun login(email: String, password: String) {

        val loginPayload = LoginPayloadDTO(email, password)
        val call = authService.login(loginPayload)

        call.enqueue(object : Callback<LoginResponseDTO> {
            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                Log.d("ONFAILURE", t.toString())
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponseDTO>,
                response: Response<LoginResponseDTO>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
                } else {
                    val bodyResponse = response.body()
                    Toast.makeText(ctx, "Bienvenido ${bodyResponse?.user?.username}", Toast.LENGTH_SHORT).show()
                    val sharedEdit = sharedPref.edit()
                    sharedEdit.putLong("user_id", bodyResponse?.user?.id!!)
                    sharedEdit.putString("user_jwt", bodyResponse?.jwt!!)
                    sharedEdit.commit()

                    val intent = Intent(ctx, ListActivity::class.java)
                    ctx.startActivity(intent)
                    (ctx as Activity).finish()
                }
            }
        })
    }

    fun register(user: User) {

        val registerPayload = RegisterPayloadDTO(
            user.email,
            user.email,
            user.password
        )

        val call = authService.register(registerPayload)

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("ONFAILURE", t.toString())
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Cuenta registrada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(ctx, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    ctx.startActivity(intent)
                }
            }
        })
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