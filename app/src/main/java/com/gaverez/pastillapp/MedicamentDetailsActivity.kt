package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.gaverez.pastillapp.models.Medicament

class MedicamentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicament_details)

        val medicament = intent.getSerializableExtra("medicament") as Medicament

        val tvName = findViewById<TextView>(R.id.activity_details_tv_med_name)
        val tvStart = findViewById<TextView>(R.id.activity_details_tv_med_start)
        val tvRepeats = findViewById<TextView>(R.id.activity_details_tv_med_repeat)
        val tvNote = findViewById<TextView>(R.id.activity_details_tv_med_note)

        tvName.text = medicament.name
        tvStart.text = "${medicament.dateStart} a las ${medicament.timeStart}"
        tvRepeats.text = "${medicament.repeatQty} ${medicament.repeatUnit}"
        tvNote.text = medicament.note

        val btnToEditMedDetails = findViewById<Button>(R.id.activity_details_btn_edit)
        btnToEditMedDetails.setOnClickListener{
            val intent = Intent(this, MedicamentEditActivity::class.java)
            intent.putExtra("medicament", medicament)
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