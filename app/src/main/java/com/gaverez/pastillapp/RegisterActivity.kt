package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.gaverez.pastillapp.controllers.AuthController
import com.gaverez.pastillapp.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val buttonToRegister = findViewById<TextView>(R.id.activity_register_btn_register)
        val tvToLogin = findViewById<TextView>(R.id.activity_register_tv_login)

        val tilFirstName = findViewById<TextInputLayout>(R.id.activity_register_til_nombre)
        val tilLastName = findViewById<TextInputLayout>(R.id.activity_register_til_apellido)
        val tilEmail = findViewById<TextInputLayout>(R.id.activity_register_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.activity_register_til_password)


        buttonToRegister.setOnClickListener{

            val firstNameValid = TilValidator(tilFirstName)
                .required()
                .minLength(2)
                .isValid()

            val lastNameValid = TilValidator(tilLastName)
                .required()
                .minLength(2)
                .isValid()

            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            val passwordValid = TilValidator(tilPassword)
                .required()
                .minLength(8)
                .isValid()

            if (firstNameValid && lastNameValid && emailValid && passwordValid) {
                val email = tilEmail.editText?.text.toString()
                val password = tilPassword.editText?.text.toString()
                val firstName = tilFirstName.editText?.text.toString()
                val lastName = tilLastName.editText?.text.toString()
                AuthController(this).register(firstName, lastName, email, password)
            } else {
                Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }

        tvToLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

    }
}