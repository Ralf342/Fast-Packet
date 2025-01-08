package com.example.fastpacketvmovil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fastpacketvmovil.interfaces.ListenerRecycleEnvios
import com.example.fastpacketvmovil.poko.Envio
import com.example.fastpacketvmovil.R

class EnvioAdapter(val envios: List<Envio>, val listenerRecycleEnvios: ListenerRecycleEnvios): RecyclerView.Adapter<EnvioAdapter.ViewHolderEnvio> (){

    class ViewHolderEnvio (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numeroGuia : TextView = itemView.findViewById(R.id.tv_texto_numero_guia)
        val destino : TextView = itemView.findViewById(R.id.tv_texto_destino)
        val estatus : TextView = itemView.findViewById(R.id.tv_texto_estatus)
        val btnDetalle : Button = itemView.findViewById(R.id.btn_detalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEnvio {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleritemenvio, parent, false)
        return ViewHolderEnvio(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolderEnvio,
        position: Int
    ) {
        val envio = envios.get(position)
        holder.numeroGuia.text = envio.numeroDeGuia.toString()
        holder.destino.text = envio.destino
        holder.estatus.text = envio.estatus
        holder.btnDetalle.setOnClickListener{
            listenerRecycleEnvios.clickDetalleEnvio(envio, position)
        }
    }

    override fun getItemCount(): Int {
        return envios.size
    }
}