package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MedicamentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_details)

        val btnToEditMedDetails = findViewById<Button>(R.id.activity_details_btn_edit)
        btnToEditMedDetails.setOnClickListener{
            //Toast.makeText(this, "prueba", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MedicamentEditActivity::class.java)
            startActivity(intent)
        }


    }
}