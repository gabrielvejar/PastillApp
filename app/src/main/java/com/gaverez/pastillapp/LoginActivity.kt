package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.gaverez.pastillapp.controllers.AuthController
import com.gaverez.pastillapp.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnToLogin = findViewById<Button>(R.id.activity_login_btn_login)
        val tvToRegister = findViewById<TextView>(R.id.activity_login_tv_register)
        val tilEmail = findViewById<TextInputLayout>(R.id.activity_login_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.activity_login_til_password)

        btnToLogin.setOnClickListener{

            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            val passwordValid = TilValidator(tilPassword)
                .required()
                .isValid()

            if (true || emailValid && passwordValid) {
                val email = tilEmail.editText?.text.toString()
                val password = tilPassword.editText?.text.toString()
                AuthController(this).login(email, password)
            } else {
                Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }

        tvToRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

    }
}