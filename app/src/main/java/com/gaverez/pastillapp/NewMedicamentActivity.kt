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

class NewMedicamentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_medicament)

        val btnToSaveNewMed = findViewById<Button>(R.id.activity_new_med_btn_save)

        val tilName = findViewById<TextInputLayout>(R.id.activity_new_med_til_name)
        val tilStartTime = findViewById<TextInputLayout>(R.id.activity_new_med_til_start_time)
        val tilDays = findViewById<TextInputLayout>(R.id.activity_new_med_til_days)
        val tilRepeatQty = findViewById<TextInputLayout>(R.id.activity_new_med_til_repeat_qty)
        val tilNote = findViewById<TextInputLayout>(R.id.activity_new_med_til_note)

        val spnRepeatUnit = findViewById<Spinner>(R.id.activity_new_med_spn_repeat_unit)
        //populate Repeat Unit spinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.repeat_units,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRepeatUnit.adapter = spinnerAdapter

        btnToSaveNewMed.setOnClickListener{
            val name = tilName.editText?.text.toString()
            val startTime = tilStartTime.editText?.text.toString()
            val days = tilDays.editText?.text.toString()
            val repeatQty = tilRepeatQty.editText?.text.toString()
            val spnRepeatUnitValue = spnRepeatUnit.selectedItem.toString()

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

            if (nameValid && startTimeValid && daysValid && rptQtyValid) {
                Toast.makeText(this, spnRepeatUnitValue, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            } else {
                //Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}