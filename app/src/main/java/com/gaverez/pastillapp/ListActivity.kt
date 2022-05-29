package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //val btnToAddNewMed = findViewById<Button>(R.id.activity_list_btn_add_med)
        //btnToAddNewMed.setOnClickListener{
        //    val intent = Intent(this, Register_Activity::class.java)
        //    startActivity(intent)
        //}

        val btnToViewMedDetails = findViewById<Button>(R.id.activity_list_btn_med1)
        btnToViewMedDetails.setOnClickListener{
            val intent = Intent(this, MedicamentDetailsActivity::class.java)
            startActivity(intent)
        }

    }
}