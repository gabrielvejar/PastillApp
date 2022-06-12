package com.gaverez.pastillapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.gaverez.pastillapp.R
import com.gaverez.pastillapp.models.Medicament

class MedicamentAdapter(private val ctx: Context, private val medicaments: List<Medicament>) : BaseAdapter() {
    override fun getCount(): Int {
        return medicaments.size
    }

    override fun getItem(i: Int): Medicament {
        return medicaments[i]
    }

    override fun getItemId(i: Int): Long {
        return medicaments[i].id!!
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(ctx)

        val rowView = inflater.inflate(R.layout.item_medicament, null)

        val medicament = medicaments[i]

        val tvName = rowView.findViewById<TextView>(R.id.item_medicament_tv_name)
        val tvId = rowView.findViewById<TextView>(R.id.item_medicament_tv_id)

        tvName.text = medicament.name
        tvId.text = medicament.id.toString()

        return rowView
    }

}