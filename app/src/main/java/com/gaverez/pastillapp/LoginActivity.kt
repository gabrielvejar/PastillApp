package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvToRegister = findViewById<TextView>(R.id.activity_login_tv_register)
        tvToRegister.setOnClickListener{
            //Toast.makeText(this, "Registrar", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }

    }
}