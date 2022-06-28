package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.gaverez.pastillapp.controllers.AuthController
import com.gaverez.pastillapp.models.User
import com.gaverez.pastillapp.utils.TilValidator
import com.gaverez.pastillapp.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

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
        val tilBirth = findViewById<TextInputLayout>(R.id.activity_register_til_birth)

        tilBirth.editText?.setOnClickListener { _ ->
            showDatePickerDialog(this, tilBirth, Date())
        }

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
                val firstname = tilFirstName.editText?.text.toString()
                val lastname = tilLastName.editText?.text.toString()
                val birth = tilBirth.editText?.text.toString()

                val user = User(
                    id = null,
                    firstname = firstname,
                    lastname = lastname,
                    email = email,
                    password = password,
                    birth = SimpleDateFormat("yyyy-MM-dd").parse(birth)
                )
                AuthController(this).register(user)
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