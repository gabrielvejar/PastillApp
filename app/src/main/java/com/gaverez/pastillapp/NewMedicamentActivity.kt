package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.gaverez.pastillapp.controllers.MedicamentController
import com.gaverez.pastillapp.models.Medicament
import com.gaverez.pastillapp.utils.TilValidator
import com.gaverez.pastillapp.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class NewMedicamentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_medicament)

        val btnToSaveNewMed = findViewById<Button>(R.id.activity_new_med_btn_save)

        val tilName = findViewById<TextInputLayout>(R.id.activity_new_med_til_name)
        val spnStartTime = findViewById<Spinner>(R.id.activity_new_med_spn_start_time)
        val tilDays = findViewById<TextInputLayout>(R.id.activity_new_med_til_days)
        val tilRepeatQty = findViewById<TextInputLayout>(R.id.activity_new_med_til_repeat_qty)
        val spnRepeatUnit = findViewById<Spinner>(R.id.activity_new_med_spn_repeat_unit)
        val tilNote = findViewById<TextInputLayout>(R.id.activity_new_med_til_note)
        val tilStartDate = findViewById<TextInputLayout>(R.id.activity_new_med_til_start_date)

        tilStartDate.editText?.setOnClickListener { _ ->
            showDatePickerDialog(this, tilStartDate, Date())
        }

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
            R.array.time_units,
            android.R.layout.simple_spinner_item
        )
        repeatUnitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRepeatUnit.adapter = repeatUnitSpinnerAdapter

        btnToSaveNewMed.setOnClickListener{
            val name = tilName.editText?.text.toString()
            val startDate = tilStartDate.editText?.text.toString()
            val startTime = spnStartTime.selectedItem.toString()
            val days = tilDays.editText?.text.toString()
            val repeatQty = tilRepeatQty.editText?.text.toString()
            val repeatUnit = spnRepeatUnit.selectedItem.toString()
            val note = tilNote.editText?.text.toString()

            val nameValid = TilValidator(tilName)
                .required()
                .isValid()

            val startDateValid = TilValidator(tilStartDate)
                .required()
                .isValid()

            val daysValid = TilValidator(tilDays)
                .required()
                .days()
                .isValid()

            val rptQtyValid = TilValidator(tilRepeatQty)
                .required()
                .isValid()

            if (nameValid && startDateValid && daysValid && rptQtyValid) {

                val newMedicament = Medicament(
                    id = null,
                    active = true,
                    name = name,
                    dateStart = startDate,
                    timeStart = startTime,
                    days = Integer.parseInt(days.trim()),
                    repeatQty = Integer.parseInt(repeatQty.trim()),
                    repeatUnit = repeatUnit,
                    note = note,
                )

                MedicamentController(this).create(newMedicament)

            } else {
                Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}