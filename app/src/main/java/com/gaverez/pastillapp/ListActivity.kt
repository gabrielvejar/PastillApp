package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.gaverez.pastillapp.controllers.MedicamentController
import com.gaverez.pastillapp.ui.MedicamentAdapter

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val btnToAddNewMed = findViewById<Button>(R.id.activity_list_btn_add_med)
        btnToAddNewMed.setOnClickListener{
            val intent = Intent(this, NewMedicamentActivity::class.java)
            startActivity(intent)
        }

        val lvMedicaments = findViewById<ListView>(R.id.activity_list_lv_medicaments)

        val allMedicaments = MedicamentController(this).getAll()

        val adapter = MedicamentAdapter(this, allMedicaments)

        lvMedicaments.adapter = adapter

        val btnToViewMedDetails = findViewById<Button>(R.id.activity_list_btn_med1)
        btnToViewMedDetails.setOnClickListener{
            val intent = Intent(this, MedicamentDetailsActivity::class.java)
            startActivity(intent)
        }

    }
}