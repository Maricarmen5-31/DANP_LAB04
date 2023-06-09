package com.example.danp_lab04.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.danp_lab04.R
import com.example.danp_lab04.entities.Country

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val name_enText = view.findViewById<TextView>(R.id.textViewName_en) as TextView
    private val name_esText = view.findViewById<TextView>(R.id.textViewName_es) as TextView
    private val continent_enText = view.findViewById<TextView>(R.id.textViewContinent_en) as TextView
    private val continent_esText = view.findViewById<TextView>(R.id.textViewContinent_es) as TextView
    private val capital_enText = view.findViewById<TextView>(R.id.textViewCapital_en) as TextView
    private val capital_esText = view.findViewById<TextView>(R.id.textViewCapital_es) as TextView
    private val dialCodeText = view.findViewById<TextView>(R.id.textViewDial_code) as TextView
    private val code2Text = view.findViewById<TextView>(R.id.textViewCode_2) as TextView
    private val code3Text = view.findViewById<TextView>(R.id.textViewCode_3) as TextView
    private val tldText = view.findViewById<TextView>(R.id.textViewTld) as TextView
    private val km2Text = view.findViewById<TextView>(R.id.textViewKm2) as TextView

    fun bind(country: Country) {
        with(country) {
            name_enText.text = name_en.toString()
            name_esText.text = name_es.toString()
            continent_enText.text = continent_en.toString()
            continent_esText.text = continent_es.toString()
            capital_enText.text = capital_en.toString()
            capital_esText.text = capital_es.toString()
            dialCodeText.text = dial_code.toString()
            code2Text.text = code_2.toString()
            code3Text.text = code_3.toString()
            tldText.text = tld.toString()
            km2Text.text = km2.toString()
        }
    }
}