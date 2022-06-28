package com.gaverez.pastillapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.gaverez.pastillapp.controllers.AuthController
import com.gaverez.pastillapp.controllers.MedicamentController
import com.gaverez.pastillapp.ui.MedicamentAdapter

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val btnToAddNewMed = findViewById<Button>(R.id.activity_list_btn_add_med)
        val btnToLogout = findViewById<Button>(R.id.activity_list_btn_logout)

        //Añadir nuevo medicamento
        btnToAddNewMed.setOnClickListener{
            val intent = Intent(this, NewMedicamentActivity::class.java)
            startActivity(intent)
        }

        //Cerrar sesión
        btnToLogout.setOnClickListener{
            AuthController(this).clearSession()
        }

        val lvMedicaments = findViewById<ListView>(R.id.activity_list_lv_medicaments)

        val allMedicaments = MedicamentController(this).getAll()

        val adapter = MedicamentAdapter(this, allMedicaments)

        lvMedicaments.adapter = adapter

        lvMedicaments.setOnItemClickListener { adapterView, view, i, l ->
            run {
                val medicament = allMedicaments[i]
                val intent = Intent(view.context, MedicamentDetailsActivity::class.java)
                intent.putExtra("medicament", medicament)
                view.context.startActivity(intent)
            }
        }


    }
}