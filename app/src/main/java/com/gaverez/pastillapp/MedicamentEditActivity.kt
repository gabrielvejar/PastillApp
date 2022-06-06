package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.gaverez.pastillapp.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class MedicamentEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_edit)

        val btnToSaveMedDetailsChanges = findViewById<Button>(R.id.activity_edit_btn_save)

        val tilName = findViewById<TextInputLayout>(R.id.activity_edit_til_name)
        val tilStartTime = findViewById<TextInputLayout>(R.id.activity_edit_til_start_time)
        val tilDays = findViewById<TextInputLayout>(R.id.activity_edit_til_days)
        val tilRepeatQty = findViewById<TextInputLayout>(R.id.activity_edit_til_repeat_qty)
        val tilRepeatUnit = findViewById<TextInputLayout>(R.id.activity_edit_til_repeat_unit)
        val tilNote = findViewById<TextInputLayout>(R.id.activity_edit_til_note)

        btnToSaveMedDetailsChanges.setOnClickListener{

            val nameValid = TilValidator(tilName)
                .required()
                .isValid()

            val startTimeValid = TilValidator(tilStartTime)
                .required()
                .time()
                .isValid()

            val daysValid = TilValidator(tilDays)
                .required()
                .days()
                .isValid()

            val rptQtyValid = TilValidator(tilRepeatQty)
                .required()
                .isValid()

            val rptUnitValid = TilValidator(tilRepeatUnit)
                .required()
                .isValid()

            if (nameValid && startTimeValid && daysValid && rptQtyValid && rptUnitValid) {
                val intent = Intent(this, MedicamentDetailsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}