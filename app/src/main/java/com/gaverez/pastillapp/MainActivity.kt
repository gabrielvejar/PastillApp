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

        //Redirect to Login
        val btnToLogin = findViewById<Button>(R.id.activity_main_btn_login)
        btnToLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //Redirect to Register
        val btnToRegister = findViewById<Button>(R.id.activity_main_btn_signin)
        btnToRegister.setOnClickListener{
            val intent = Intent(this, Register_Activity::class.java)
            startActivity(intent)
        }
    }
}