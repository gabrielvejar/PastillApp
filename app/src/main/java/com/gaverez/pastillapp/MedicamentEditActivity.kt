package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MedicamentEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_edit)

        val btnToSaveMedDetailsChanges = findViewById<Button>(R.id.activity_edit_btn_save)
        btnToSaveMedDetailsChanges.setOnClickListener{
            val intent = Intent(this, MedicamentDetailsActivity::class.java)
            startActivity(intent)
        }

        val btnToDeleteMed = findViewById<Button>(R.id.activity_edit_btn_delete)
        btnToDeleteMed.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}