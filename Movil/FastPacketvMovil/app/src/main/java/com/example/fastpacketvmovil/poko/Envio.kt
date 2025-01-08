package com.example.fastpacketvmovil.poko

import java.sql.Timestamp
import java.time.LocalDateTime

data class Envio(
    val numeroDeGuia: Int,
    val costo: Float,
    val ciudadOrigen: String?,
    val estadoOrigen: String?,
    val calleOrigen: String?,
    val coloniaOrigen: String?,
    val numeroCasaOrigen: Int?,
    val codigoPostalOrigen: Int?,
    val idClienteDestino: Int,
    val correo: String,
    val destino: String?,
    val fechaModificacion: String?,
    val idUnidad: Int,
    val idColaboradorModificacion: Int,
    val idEstatus: Int,
    val estatus: String?,
    val motivo: String
)
