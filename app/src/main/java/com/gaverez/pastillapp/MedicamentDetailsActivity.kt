package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MedicamentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_details)

        val btnToEditMedDetails = findViewById<Button>(R.id.activity_details_btn_edit)
        btnToEditMedDetails.setOnClickListener{
            val intent = Intent(this, MedicamentEditActivity::class.java)
            startActivity(intent)
        }

        val btnToDeleteMed = findViewById<Button>(R.id.activity_details_btn_delete)
        btnToDeleteMed.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

    }
}