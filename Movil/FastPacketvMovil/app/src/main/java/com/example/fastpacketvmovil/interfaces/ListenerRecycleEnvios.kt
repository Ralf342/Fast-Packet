package com.example.fastpacketvmovil.interfaces

import com.example.fastpacketvmovil.poko.Envio

interface ListenerRecycleEnvios {
    fun clickDetalleEnvio(envio: Envio, position: Int)
}