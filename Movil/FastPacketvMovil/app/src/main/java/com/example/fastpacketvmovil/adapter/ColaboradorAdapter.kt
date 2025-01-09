package com.example.fastpacketvmovil.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fastpacketvmovil.poko.Envio


class ColaboradorAdapter(
    context: Context,
    private val envios: List<Envio>
) : ArrayAdapter<Envio>(context, android.R.layout.simple_spinner_item, envios) {

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getCount(): Int {
        return envios.size
    }

    override fun getItem(position: Int): Envio {
        return envios[position]
    }

    override fun getItemId(position: Int): Long {
        return envios[position].idColaboradorModificacion.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val envio = envios[position]
        textView.text = envio.idColaboradorModificacion.toString()
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val envio = envios[position]
        textView.text = envio.idColaboradorModificacion.toString()
        return view
    }
}


