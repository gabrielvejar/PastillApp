package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.gaverez.pastillapp.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class MedicamentEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_edit)

        val btnToSaveMedDetailsChanges = findViewById<Button>(R.id.activity_edit_btn_save)

        val tilName = findViewById<TextInputLayout>(R.id.activity_edit_til_name)
        val spnStartTime = findViewById<Spinner>(R.id.activity_edit_spn_start_time)
        val tilDays = findViewById<TextInputLayout>(R.id.activity_edit_til_days)
        val tilRepeatQty = findViewById<TextInputLayout>(R.id.activity_edit_til_repeat_qty)
        val tilNote = findViewById<TextInputLayout>(R.id.activity_edit_til_note)
        val spnRepeatUnit = findViewById<Spinner>(R.id.activity_edit_spn_repeat_unit)

        //populate Start Time spinner
        val startTimeSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.hours,
            android.R.layout.simple_spinner_item
        )
        startTimeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnStartTime.adapter = startTimeSpinnerAdapter

        //populate Repeat Unit spinner
        val repeatUnitSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.repeat_units,
            android.R.layout.simple_spinner_item
        )
        repeatUnitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRepeatUnit.adapter = repeatUnitSpinnerAdapter


        btnToSaveMedDetailsChanges.setOnClickListener{
            val name = tilName.editText?.text.toString()
            val startTime = spnStartTime.selectedItem.toString()
            val days = tilDays.editText?.text.toString()
            val repeatQty = tilRepeatQty.editText?.text.toString()
            val repeatUnit = spnRepeatUnit.selectedItem.toString()

            val nameValid = TilValidator(tilName)
                .required()
                .isValid()

            val daysValid = TilValidator(tilDays)
                .required()
                .days()
                .isValid()

            val rptQtyValid = TilValidator(tilRepeatQty)
                .required()
                .isValid()

            if (nameValid && daysValid && rptQtyValid) {
                Toast.makeText(this, "Hora inicio: $startTime, Unidad tiempo: $repeatUnit", Toast.LENGTH_SHORT).show()
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