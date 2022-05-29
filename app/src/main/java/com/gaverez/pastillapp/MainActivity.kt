package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToLogin = findViewById<Button>(R.id.activity_main_btn_login)
        btnToLogin.setOnClickListener{
            //Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btnToRegister = findViewById<Button>(R.id.activity_main_btn_signin)
        btnToRegister.setOnClickListener{
            //Toast.makeText(this, "Registrar", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
    }
}