package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.gaverez.pastillapp.controllers.MedicamentController
import com.gaverez.pastillapp.models.Medicament
import com.gaverez.pastillapp.utils.TilValidator
import com.gaverez.pastillapp.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class MedicamentEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_edit)

        val medicament = intent.getSerializableExtra("medicament") as Medicament

        val btnToSaveMedDetailsChanges = findViewById<Button>(R.id.activity_edit_btn_save)

        val tilName = findViewById<TextInputLayout>(R.id.activity_edit_til_name)
        val tilStartDate = findViewById<TextInputLayout>(R.id.activity_edit_til_start_date)
        val spnStartTime = findViewById<Spinner>(R.id.activity_edit_spn_start_time)
        val tilDays = findViewById<TextInputLayout>(R.id.activity_edit_til_days)
        val tilRepeatQty = findViewById<TextInputLayout>(R.id.activity_edit_til_repeat_qty)
        val tilNote = findViewById<TextInputLayout>(R.id.activity_edit_til_note)
        val spnRepeatUnit = findViewById<Spinner>(R.id.activity_edit_spn_repeat_unit)

        //Abrir dialog selector de fecha al presionar tilStartDate
        tilStartDate.editText?.setOnClickListener { _ ->
            showDatePickerDialog(this, tilStartDate, Date())
        }

        //popular Start Time spinner
        val startTimeSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.hours,
            android.R.layout.simple_spinner_item
        )
        startTimeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnStartTime.adapter = startTimeSpinnerAdapter

        //popular Repeat Unit spinner
        val repeatUnitSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.time_units,
            android.R.layout.simple_spinner_item
        )
        repeatUnitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnRepeatUnit.adapter = repeatUnitSpinnerAdapter

        //Mostrar valores guardados en los inputs
        //nombre
        tilName.editText?.setText(medicament.name)
        //fecha de inicio
        tilStartDate.editText?.setText(medicament.dateStart)
        //spinner hora de inicio
        spnStartTime.setSelection(startTimeSpinnerAdapter.getPosition(medicament.timeStart))
        //dias de tratamiento
        tilDays.editText?.setText(medicament.days.toString())
        //repeticiones
        tilRepeatQty.editText?.setText(medicament.repeatQty.toString())
        //spinner repeat time unit
        spnRepeatUnit.setSelection(repeatUnitSpinnerAdapter.getPosition(medicament.repeatUnit))
        //nota
        tilNote.editText?.setText(medicament.note)

        btnToSaveMedDetailsChanges.setOnClickListener{
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

                val medicament = Medicament(
                    id = medicament.id,
                    active = true,
                    name = name,
                    dateStart = startDate,
                    timeStart = startTime,
                    days = Integer.parseInt(days.trim()),
                    repeatQty = Integer.parseInt(repeatQty.trim()),
                    repeatUnit = repeatUnit,
                    note = note,
                )

                MedicamentController(this).update(medicament)
            } else {
                Toast.makeText(this, "Revise datos ingresados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}